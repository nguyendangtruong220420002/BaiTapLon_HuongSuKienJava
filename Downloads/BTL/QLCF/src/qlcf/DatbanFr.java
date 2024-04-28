
package qlcf;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import connectBD.Connect_DB;
import dao.DatBanDAO;
import dao.NhanVienDAO;
import entiy.DatBan;

public class DatbanFr extends JFrame {

    private static final long serialVersionUID = 1L;
	private Detail detail;
    private ResultSet rs=null;
    private static DatBanDAO datBanDAO;
    private List<DatBan> datBanList;
    
    private boolean add=false, change=false;
      
	private JTextField tfTimes;
	private JButton btnAdd;
    private JButton btnCancel;
    private JButton btnDel;
    private JButton btnEdit;
    private JButton btnHome;
    private JButton btnSave;
    private JComboBox<String> cboSoban;
    private JComboBox<String> cbxHours;
    private JComboBox<String> cbxMinute;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JScrollPane jScrollPane1;
    private JLabel lbDay;
    private JLabel lbSDT;
    private JLabel lbSoban;
    private JLabel lbTenkhach;
    private JLabel lbThanhtoan;
    private JLabel lbTime;
    private JLabel lbTrangthai;
    private JLabel lbl2Cham;
    private JRadioButton radDathanhtoan;
    private JRadioButton radNo;
    private static JTable tableDatban;
    private JDateChooser tfDay;
    private JTextField tfNote;
    private JTextField tfSDT;
    private JTextField tfTenkhach;
   
    public DatbanFr(DatBanDAO datBanDAO) {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(this);
        DatbanFr.datBanDAO = datBanDAO;
        detail=new Detail();
        Disabled();
        loadBan();
        Load();
    }
    
    private String cutChar(String arry){
        return arry.replaceAll("\\D+","");
    }
    
    private void Load(){
    	try {
			datBanList = datBanDAO.getAllDatBan();
			displayDataInTable();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    private void displayDataInTable() {
		// TODO Auto-generated method stub
    	String[] arry={"Tên khách hàng","SĐT","Bàn","Thời gian","Ngày","Thanh toán","Ghi Chú"};
    	DefaultTableModel tableModel=new DefaultTableModel(arry,0); 
    	tableDatban.setModel(tableModel);
    	tableModel.setRowCount(0);
    	for(DatBan datBan : datBanList) {
    		Object [] rowData = {
    				datBan.getTenKH(),
    				datBan.getSdt(),
    				datBan.getBan(),
    				datBan.getThoiGian(),
    				datBan.getNgay(),
    				datBan.getThanhToan(),
    				datBan.getGhiChu(),
    		};
    		tableModel.addRow(rowData);
    	}
	}

	private void reset(){
        loadBan();
        loadHours();
        loadMinute();
        tfTenkhach.setText("");
        tfSDT.setText("");
        cboSoban.setSelectedIndex(0);
        cbxHours.setSelectedIndex(0);
        cbxMinute.setSelectedIndex(0);
        radNo.setSelected(false);
        radDathanhtoan.setSelected(false);
        tfNote.setText("");
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(false);
        btnDel.setEnabled(false);
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        lbTrangthai.setText("Trạng thái");  
    }
    
	private boolean checkNull() {
	    if (tfTenkhach.getText().equals("")) {
	        lbTrangthai.setText("Bạn chưa nhập tên khách hàng");
	        return false;
	    } else if (tfSDT.getText().equals("")) {
	        lbTrangthai.setText("Bạn cần phải nhập số điện thoại của khách");
	        return false;
	    } else if (cboSoban.getSelectedItem() == null) {
	        lbTrangthai.setText("Bạn cần chọn bàn cho khách");
	        return false;
	    } else if (((String) cbxHours.getSelectedItem()).equals("")) {
	        lbTrangthai.setText("Chưa chọn giờ đặt bàn của khách");
	        return false;
	    } else if (((String) cbxMinute.getSelectedItem()).equals("")) {
	        lbTrangthai.setText("Chưa chọn phút đặt bàn của khách");
	        return false;
	    } else if (((JTextField) tfDay.getDateEditor().getUiComponent()).getText().equals("")) {
	        lbTrangthai.setText("Chưa ấn định ngày đặt bàn");
	        return false;
	    } else if (radNo.isSelected() == false && radDathanhtoan.isSelected() == false) {
	        lbTrangthai.setText("Bạn chưa chọn tình trạng thanh toán!");
	        return false;
	    }
	    return true;
	}
    private void Enabled(){
        tfTenkhach.setEnabled(true);
        tfSDT.setEnabled(true);
        cboSoban.setEnabled(true);
        cbxHours.setEnabled(true);
        cbxMinute.setEnabled(true);
        tfDay.setEnabled(true);
        radNo.setEnabled(true);
        radDathanhtoan.setEnabled(true);
        tfNote.setEnabled(true);
    }
    
    private void Disabled(){
        tfTenkhach.setEnabled(false);
        tfSDT.setEnabled(false);
        cboSoban.setEnabled(false);
        cbxHours.setEnabled(false);
        cbxMinute.setEnabled(false);
        tfDay.setEnabled(false);
        radNo.setEnabled(false);
        radDathanhtoan.setEnabled(false);
        tfNote.setEnabled(false);
    }
    
    private String thanhtoan(){
        if(radNo.isSelected()){
            return radNo.getText();
        }else
            return radDathanhtoan.getText();
    }
    
    private void checkThanhtoan(String tt){
        if(tt.equals("Không")){
            radNo.setSelected(true);
        }else
            radDathanhtoan.setSelected(true);
    }
    
    private void loadBan(){
        cboSoban.removeAllItems();
         
        for(int i=1;i <= 25;i++){
            cboSoban.addItem(String.valueOf(i));
        }
    }
    
    private void loadHours(){
        cbxHours.removeAllItems();
        
        for(int i=0;i <= 23;i++){
            cbxHours.addItem(String.valueOf(i));
        }
    }
    
    private void loadMinute(){
        cbxMinute.removeAllItems();
        
        for(int i=0;i <= 59;i++){
            if(i<10){
                cbxMinute.addItem(String.valueOf("0"+i));
            }
            else cbxMinute.addItem(String.valueOf(i));
        }
    }
    
    private void addCustomer(){
        if(checkNull()){
            try {
            	DatBan newDatBan = createDatBanFromInput();
				datBanDAO.addDatBan(newDatBan);
				 reset();
		          Load();
		          Disabled();
		          lbTrangthai.setText("Đã thêm thành công khách hàng " +tfTenkhach.getText());		        
			} catch (SQLException e) {
				e.printStackTrace();
				lbTrangthai.setText("Lỗi: Không thể thêm khách hàng vào dược !!!");
			}      
        }
    }
    private DatBan createDatBanFromInput() {
        DatBan newDatBan = new DatBan();
        newDatBan.setTenKH(tfTenkhach.getText());
        newDatBan.setSdt(tfSDT.getText());
        String banString = (String) cboSoban.getSelectedItem();
        try {
            int ban = Integer.parseInt(banString);
            newDatBan.setBan(ban);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.err.println("Không thể chuyển đổi '" + banString + "' thành số nguyên.");
        }
        newDatBan.setNgay(tfDay.getDate());      
        String thoiGian = (String) cbxHours.getSelectedItem() + ":" + (String) cbxMinute.getSelectedItem();
        newDatBan.setThoiGian(thoiGian);
        if (radNo.isSelected()) {
            newDatBan.setThanhToan("Không");
        } else if (radDathanhtoan.isSelected()) {
            newDatBan.setThanhToan("Đã thanh toán");
        }
        newDatBan.setGhiChu(tfNote.getText());
        return newDatBan;
    }

    private void editCustomer() {
    	int selectedRow = tableDatban.getSelectedRow();
    	if(selectedRow !=-1) {
    		DatBan selecteDatBan = datBanList.get(selectedRow);
    		if (checkNull()) {
                selecteDatBan.setTenKH(tfTenkhach.getText());
                selecteDatBan.setSdt(tfSDT.getText());
                String banString = (String) cboSoban.getSelectedItem();
                try {
                    int ban = Integer.parseInt(banString);
                    selecteDatBan.setBan(ban);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.err.println("Không thể chuyển đổi '" + banString + "' thành số nguyên.");
                }
                selecteDatBan.setNgay(tfDay.getDate());      
                String thoiGian = (String) cbxHours.getSelectedItem() + ":" + (String) cbxMinute.getSelectedItem();
                selecteDatBan.setThoiGian(thoiGian);
                if (radNo.isSelected()) {
                    selecteDatBan.setThanhToan("Không");
                } else if (radDathanhtoan.isSelected()) {
                    selecteDatBan.setThanhToan("Đã thanh toán");
                }
                selecteDatBan.setGhiChu(tfNote.getText());
                try {
                    datBanDAO.updateDatBan(selecteDatBan);
                    reset();
                    Load();
                    Disabled();
                    lbTrangthai.setText("Sửa thông tin khách hàng thành công!");
                } catch (SQLException e) {
                    e.printStackTrace();
                    lbTrangthai.setText("Lỗi: Không thể sửa thông tin khách hàng!");
                }
            }
    	}  else {
			lbTrangthai.setText("Vui lòng chọn một nhân viên để sửa!");
		}
    }
    private boolean check(){
        try {
        	String sql = "SELECT * FROM DatBan";
			rs = Connect_DB.getInstance().executeQuery(sql);
            while(rs.next()){
                if(rs.getString("sdt").toString().trim().equals(tfSDT.getText())){
                    lbTrangthai.setText("Khách hàng đã tồn tại");
                    return false;
                }  
            }      
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
        
    private void initComponents() {

        jLabel1 = new JLabel();
        jPanel1 = new JPanel();
        lbTenkhach = new JLabel();
        lbSoban = new JLabel();
        lbTime = new JLabel();
        lbDay = new JLabel();
        tfTenkhach = new JTextField();
        cboSoban = new JComboBox<>();
        tfSDT = new JTextField();
        lbSDT = new JLabel();
        lbThanhtoan = new JLabel();
        radNo = new JRadioButton();
        radDathanhtoan = new JRadioButton();
        jPanel2 = new JPanel();
        tfNote = new JTextField();
        jPanel4 = new JPanel();
        jScrollPane1 = new JScrollPane();
        tableDatban = new JTable();
        tfDay = new JDateChooser();
        tfTimes = new JTextField();
        cbxHours = new JComboBox<>();
        cbxMinute = new JComboBox<>();
        lbl2Cham = new JLabel();
        jPanel3 = new JPanel();
        btnSave = new JButton();
        btnCancel = new JButton();
        btnAdd = new JButton();
        btnEdit = new JButton();
        btnDel = new JButton();
        btnHome = new JButton();
        lbTrangthai = new JLabel();
        
        

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đặt bàn");

        jLabel1.setFont(new Font("Times New Roman", 0, 48));
        jLabel1.setText("ĐẶT BÀN");

        lbTenkhach.setFont(new Font("Tahoma", 0, 14));
        lbTenkhach.setText("Tên khách hàng:");

        lbSoban.setFont(new Font("Tahoma", 0, 14));
        lbSoban.setText("Bàn số:");

        lbTime.setFont(new Font("Tahoma", 0, 14));
        lbTime.setText("Thời gian:");

        lbDay.setFont(new Font("Tahoma", 0, 14));
        lbDay.setText("Ngày:");

        tfTenkhach.setFont(new Font("Tahoma", 0, 14));

        cboSoban.setFont(new Font("Tahoma", 0, 14));

        tfSDT.setFont(new Font("Tahoma", 0, 14));
        tfSDT.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                tfSDTKeyReleased();
            }
        });

        lbSDT.setFont(new Font("Tahoma", 0, 14));
        lbSDT.setText("Số điện thoại:");

        lbThanhtoan.setFont(new Font("Tahoma", 0, 14));
        lbThanhtoan.setText("Trả trước:");

        radNo.setFont(new Font("Tahoma", 0, 14));
        radNo.setText("Không");
        radNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                radNoActionPerformed();
            }
        });

        radDathanhtoan.setFont(new Font("Tahoma", 0, 14));
        radDathanhtoan.setText("Đã thanh toán");
        radDathanhtoan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                radDathanhtoanActionPerformed();
            }
        });

        jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Ghi chú", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 0, 14)));

        tfNote.setFont(new Font("Tahoma", 0, 14));

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfNote, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(tfNote)
                .addContainerGap())
        );

        tableDatban.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tableDatban.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tableDatbanMouseClicked();
            }
        });
        jScrollPane1.setViewportView(tableDatban);

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 648, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        tfDay.setDateFormatString("yyyy-MM-dd");

        cbxHours.setFont(new Font("Tahoma", 0, 12));

        cbxMinute.setFont(new Font("Tahoma", 0, 12));

        lbl2Cham.setFont(new Font("Tahoma", 0, 18));
        lbl2Cham.setText(":");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(lbThanhtoan)
                            .addComponent(lbSDT)
                            .addComponent(lbSoban)
                            .addComponent(lbTenkhach)
                            .addComponent(lbTime)
                            .addComponent(lbDay))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfSDT)
                            .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(radNo)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radDathanhtoan))
                            .addComponent(cboSoban, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfTenkhach)
                            .addComponent(tfDay, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbxHours, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl2Cham, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxMinute, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTenkhach)
                            .addComponent(tfTenkhach, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSDT)
                            .addComponent(tfSDT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSoban)
                            .addComponent(cboSoban, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTime)
                            .addComponent(cbxHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxMinute, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl2Cham))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lbDay)
                            .addComponent(tfDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(lbThanhtoan)
                            .addComponent(radNo)
                            .addComponent(radDathanhtoan))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        btnSave.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        btnSave.setIcon(new ImageIcon(getClass().getResource("/Photos/floppy-disk (1).png"))); // NOI18N
        btnSave.setEnabled(false);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSaveActionPerformed();
            }
        });

        btnCancel.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        btnCancel.setIcon(new ImageIcon(getClass().getResource("/Photos/x-button.png"))); // NOI18N
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCancelActionPerformed();
            }
        });

        btnAdd.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setIcon(new ImageIcon(getClass().getResource("/Photos/plus.png"))); // NOI18N
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAddActionPerformed();
            }
        });

        btnEdit.setIcon(new ImageIcon(getClass().getResource("/Photos/edit.png"))); // NOI18N
        btnEdit.setEnabled(false);
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnEditActionPerformed();
            }
        });

        btnDel.setIcon(new ImageIcon(getClass().getResource("/Photos/delete.png"))); // NOI18N
        btnDel.setEnabled(false);
        btnDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
					btnDelActionPerformed();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnHome.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        btnHome.setIcon(new ImageIcon(getClass().getResource("/Photos/smart-home.png"))); // NOI18N
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnHomeActionPerformed();
            }
        });

        lbTrangthai.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        lbTrangthai.setHorizontalAlignment(SwingConstants.CENTER);
        lbTrangthai.setText("Trạng thái");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbTrangthai, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnHome)
                                .addGap(350, 350, 350)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnHome)
                        .addGap(44, 44, 44))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTrangthai)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        try {
			Connect_DB.getInstance().connet();
			System.out.println("Connect QuanLiDatBan Thành Công !!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	private void btnAddActionPerformed() {
        reset();
        add=true;
        change=true;
        Enabled();
        btnSave.setEnabled(true);   
        btnAdd.setEnabled(false);
        btnCancel.setEnabled(true);
    }

    private void radNoActionPerformed() {
        radNo.setSelected(true);
        radDathanhtoan.setSelected(false);
    }

    private void radDathanhtoanActionPerformed() {
        radDathanhtoan.setSelected(true);
        radNo.setSelected(false);
    }

    private void btnEditActionPerformed() {
        loadBan();
        loadHours();
        loadMinute();
        
        add=false;
        change=true;
        Enabled();
        btnAdd.setEnabled(false);
        btnDel.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
    }

    private void btnDelActionPerformed() throws SQLException {
    	add=false;
        change=true;
        btnSave.setEnabled(true); 
        int click=JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa khách hàng này không?", "Thông báo", 2);
        if(click==JOptionPane.YES_OPTION){
            String sdt = tfSDT.getText();
            datBanDAO.deleteDatBan(sdt);
            reset();
            Load();
            Disabled();
            lbTrangthai.setText("Xóa thành công khách hàng");
        }
        else reset();
    }

    private void btnSaveActionPerformed() {
    	

        if(add==true){
            if(check()){
                addCustomer();
            }
        }
        else{
            if(change==true)
                editCustomer();
        }
    }

    private void btnHomeActionPerformed() {
        MainFr home = new MainFr(detail);
        this.setVisible(false);
        home.setVisible(true);
    }

    private void tableDatbanMouseClicked() {
    	
        cboSoban.removeAllItems();
        cbxHours.removeAllItems();
        cbxMinute.removeAllItems();
        
        int click=tableDatban.getSelectedRow();
        TableModel model=tableDatban.getModel();
        
        tfTenkhach.setText(model.getValueAt(click, 0).toString().trim());
        tfSDT.setText(model.getValueAt(click, 1).toString().trim());
        cboSoban.addItem(model.getValueAt(click, 2).toString().trim());
        
        String[] s=model.getValueAt(click, 3).toString().split(":");
        
        
        cbxHours.addItem(s[0].trim());
        cbxMinute.addItem(s[1].trim());
        
        ((JTextField)tfDay.getDateEditor().getUiComponent()).setText(model.getValueAt(click, 4).toString().trim());
        tfNote.setText(model.getValueAt(click, 6).toString().trim());
        checkThanhtoan(model.getValueAt(click, 5).toString().trim());
        
        
        btnEdit.setEnabled(true);
        btnDel.setEnabled(true);
    }

    private void btnCancelActionPerformed() {
    	
        reset();
        Disabled();
        Load();
    }

    private void tfSDTKeyReleased() {
        tfSDT.setText(cutChar(tfSDT.getText()));
        
        if(tfSDT.getText().length()==11 || tfSDT.getText().length()==10 ){
            btnSave.setEnabled(true);
            lbTrangthai.setText("Số điện thoại đã hợp lệ!!");
        }
        else
        if(tfSDT.getText().length()>11 || tfSDT.getText().length()<10){
            btnSave.setEnabled(false);
            lbTrangthai.setText("Số điện thoại không được ít hơn 10 số hoặc vượt quá 11 số!!");
        }
    }

    public static void main(String args[]) throws SQLException {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                   UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
           Logger.getLogger(DatbanFr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
           Logger.getLogger(DatbanFr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
           Logger.getLogger(DatbanFr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
          Logger.getLogger(DatbanFr.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connect_DB connectDB = Connect_DB.getInstance();
		connectDB.connet();
		// Kết nối với cơ sở dữ liệu
		DatBanDAO datBanDAO = new DatBanDAO(Connect_DB.getConnection());
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                DatbanFr datbanFr = new DatbanFr(datBanDAO);
                datbanFr.setVisible(true);
            }
        });
    }

    
    
}
