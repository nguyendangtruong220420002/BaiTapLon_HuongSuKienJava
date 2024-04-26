
package qlcf;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import connectBD.Connect_DB;
import dao.NhanVienDAO;
import entiy.NhanVien;

public class qlnvFr extends JFrame {

	private static final long serialVersionUID = 1L;
	private static NhanVienDAO nhanVienDAO;
	private List<NhanVien> nhanVienList;
	private DefaultTableModel tableModel;
	private Detail detail;

	private JButton btnAdd;
	private JButton btnBack;
	private JButton btnCancel;
	private JButton btnDel;
	private JButton btnEdit;
	private JButton btnFind;
	private JButton btnSave;
	private ButtonGroup buttonGroup1;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JScrollPane jScrollPane1;
	private JLabel lbDiachi;
	private JLabel lbGioitinh;
	private JLabel lbHoten;
	private JLabel lbMaNV;
	private JLabel lbNgaysinh;
	private JLabel lbQLNV;
	private JLabel lbSDT;
	private JLabel lbTrangthai;
	private JPasswordField pass;
	private JPasswordField passConfirm;
	private JRadioButton rbNam;
	private JRadioButton rbNu;
	private static JTable tableNV;
	private JTextField tfDiachi;
	private JTextField tfFind;
	private JTextField tfHoten;
	private JTextField tfMaNV;
	private JDateChooser tfNgaysinh;
	private JTextField tfSDT;
	private JTextField tfTaikhoan;
	private boolean add;
	private boolean change;
	private ResultSet rs = null;

	public qlnvFr(NhanVienDAO nhanVienDAO) {
		initComponents();
		setResizable(false);
		setLocationRelativeTo(this);
		qlnvFr.nhanVienDAO = nhanVienDAO;
		loadData();
		this.detail = new Detail();
	}

	private void loadData() {
		try {
			nhanVienList = nhanVienDAO.getAllNhanVien();
			displayDataInTable();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	private void displayDataInTable() {
		String[] arry = { "Mã Nhân Viên", "Họ Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Địa Chỉ" };
		tableModel = new DefaultTableModel(arry, 0);
		tableNV.setModel(tableModel);
		tableModel.setRowCount(0);
		for (NhanVien nhanVien : nhanVienList) {
			Object[] rowData = { nhanVien.getMaNV(), nhanVien.getTenNV(), nhanVien.getGioiTinh(),
					nhanVien.getNgaySinh(), nhanVien.getSdt(), nhanVien.getDiaChi(), nhanVien.getTaiKhoan(),
					nhanVien.getMatKhau() };
			tableModel.addRow(rowData);
		}

	}

	private void Enabled() {
		tfMaNV.setEnabled(true);
		tfHoten.setEnabled(true);
		tfNgaysinh.setEnabled(true);
		tfSDT.setEnabled(true);
		tfTaikhoan.setEnabled(true);
		pass.setEnabled(true);
		passConfirm.setEnabled(true);
		tfDiachi.setEnabled(true);
		rbNu.setEnabled(true);
		rbNam.setEnabled(true);
	}

	private void Disabled() {
		tfMaNV.setEnabled(false);
		tfHoten.setEnabled(false);
		tfNgaysinh.setEnabled(false);
		tfSDT.setEnabled(false);
		tfTaikhoan.setEnabled(false);
		pass.setEnabled(false);
		passConfirm.setEnabled(false);
		tfDiachi.setEnabled(false);
		rbNu.setEnabled(false);
		rbNam.setEnabled(false);
	}

	private void reset() {
		add = false;
		change = false;
		tfMaNV.setText("");
		tfHoten.setText("");
		((JTextField) tfNgaysinh.getDateEditor().getUiComponent()).setText("");
		tfSDT.setText("");
		tfTaikhoan.setText("");
		pass.setText("");
		passConfirm.setText("");
		tfDiachi.setText("");
		lbTrangthai.setText("Trạng Thái");
		rbNam.setSelected(false);
		btnEdit.setEnabled(false);
		btnDel.setEnabled(false);
		btnSave.setEnabled(false);
		btnCancel.setEnabled(false);
		btnAdd.setEnabled(true);
	}

	private void checkGT(String GT) {
		if (GT.equals("Nam"))
			rbNam.setSelected(true);
		else
			rbNu.setSelected(true);
	}

	private boolean checkNull() {
		if (tfMaNV.getText().equals("")) {
			lbTrangthai.setText("Bạn chưa nhập mã nhân viên!");
			return false;
		} else if (tfHoten.getText().equals("")) {
			lbTrangthai.setText("Bạn chưa nhập họ tên nhân viên!");
			return false;
		} else if (rbNam.isSelected() == false && rbNu.isSelected() == false) {
			lbTrangthai.setText("Bạn chưa chọn giới tính!");
			return false;
		} else if (((JTextField) tfNgaysinh.getDateEditor().getUiComponent()).getText().equals("")) {
			lbTrangthai.setText("Bạn chưa nhập ngày sinh!");
			return false;
		} else if (tfSDT.getText().equals("")) {
			lbTrangthai.setText("Bạn chưa nhập số điện thoại!");
			return false;
		} else if (tfDiachi.getText().equals("")) {
			lbTrangthai.setText("Bạn chưa nhập địa chỉ!");
			return false;
		} else if (tfTaikhoan.getText().equals("")) {
			lbTrangthai.setText("Bạn chưa nhập tài khoản!");
			return false;
		} else if (pass.getText().equals("")) {
			lbTrangthai.setText("Bạn chưa nhập mật khẩu!");
			return false;
		} else if (passConfirm.getText().equals("")) {
			lbTrangthai.setText("Bạn chưa nhập lại mật khẩu!");
			return false;
		} else if (String.valueOf(pass.getPassword()).equals(String.valueOf(passConfirm.getPassword()))) {
			return true;
		} else {
			lbTrangthai.setText("Nhập lại mật khẩu không đúng!");
			return false;
		}
	}

	private String gioiTinh() {
		if (rbNam.isSelected())
			return rbNam.getText();
		else
			return rbNu.getText();
	}

	private void addNV() {
		if (checkNull()) {
			try {
				NhanVien newNhanVien = createNhanVienFromInput(); // Tạo đối tượng nhân viên từ dữ liệu nhập vào
				nhanVienDAO.addNhanVien(newNhanVien); // Thêm nhân viên mới vào cơ sở dữ liệu
				reset(); // Đặt lại giao diện sau khi thêm thành công
				loadData(); // Tải lại dữ liệu từ cơ sở dữ liệu để hiển thị
				Disabled(); // Vô hiệu hóa các trường nhập liệu
				lbTrangthai.setText("Thêm nhân viên thành công!");
			} catch (SQLException ex) {
				ex.printStackTrace();
				lbTrangthai.setText("Lỗi: Không thể thêm nhân viên vào cơ sở dữ liệu!");
			}
		}
	}

	private NhanVien createNhanVienFromInput() {
		NhanVien newNhanVien = new NhanVien();
		newNhanVien.setMaNV(tfMaNV.getText());
		newNhanVien.setTenNV(tfHoten.getText());
		newNhanVien.setGioiTinh(gioiTinh());
		newNhanVien.setNgaySinh(tfNgaysinh.getDate());
		newNhanVien.setSdt(tfSDT.getText());
		newNhanVien.setDiaChi(tfDiachi.getText());
		newNhanVien.setTaiKhoan(tfTaikhoan.getText());
		newNhanVien.setMatKhau(String.valueOf(pass.getPassword()));
		return newNhanVien;
	}

	private void changeNV() {
		int selectedRow = tableNV.getSelectedRow();
		if (selectedRow != -1) {
			NhanVien selectedNhanVien = nhanVienList.get(selectedRow);

			if (checkNull()) {
				selectedNhanVien.setMaNV(tfMaNV.getText());
				selectedNhanVien.setTenNV(tfHoten.getText());
				selectedNhanVien.setGioiTinh(gioiTinh());
				selectedNhanVien.setNgaySinh(tfNgaysinh.getDate());
				selectedNhanVien.setSdt(tfSDT.getText());
				selectedNhanVien.setDiaChi(tfDiachi.getText());
				selectedNhanVien.setTaiKhoan(tfTaikhoan.getText());
				selectedNhanVien.setMatKhau(String.valueOf(pass.getPassword()));

				try {
					nhanVienDAO.updateNhanVien(selectedNhanVien);
					reset();
					loadData();
					Disabled();
					lbTrangthai.setText("Sửa thông tin nhân viên thành công!");
				} catch (SQLException ex) {
					ex.printStackTrace();
					lbTrangthai.setText("Lỗi: Không thể cập nhật thông tin nhân viên!");
				}
			}
		} else {
			lbTrangthai.setText("Vui lòng chọn một nhân viên để sửa!");
		}
	}

	private boolean check() {
		try {
			String sql = "SELECT * FROM QLNV";
			rs = Connect_DB.getInstance().executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("maNV").toString().trim().equals(tfMaNV.getText())) {
					lbTrangthai.setText("Mã nhân viên bạn nhập đã tồn tại!");
					return false;
				} else if (rs.getString("taiKhoan").toString().trim().equals(tfTaikhoan.getText())) {
					lbTrangthai.setText("Tài khoản bạn nhập đã tồn tại!");
					return false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	private void loadAccount(String s) {
		try {
			String sql = "SELECT * FROM QLNV";
			rs = Connect_DB.getInstance().executeQuery(sql);
			while (rs.next()) {
				tfTaikhoan.setText(rs.getString("taiKhoan").trim());
				pass.setText(rs.getString("matKhau").trim());
				passConfirm.setText(rs.getString("matKhau").trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

//    private void checkDecentralization(){
//        if(String.valueOf(detail.getUser().substring(0, 4)).equals("User")){
//            btnAdd.setEnabled(false);
//            btnEdit.setEnabled(false);
//            btnDel.setEnabled(false);
//            btnSave.setEnabled(false);
//            btnCancel.setEnabled(false);
//        }
//    }
	private String cutChar(String arry) {
		return arry.replaceAll("\\D+", "");
	}

	private void initComponents() {

		buttonGroup1 = new ButtonGroup();
		lbQLNV = new JLabel();
		jScrollPane1 = new JScrollPane();
		tableNV = new JTable();
		jPanel1 = new JPanel();
		lbMaNV = new JLabel();
		lbHoten = new JLabel();
		lbGioitinh = new JLabel();
		lbNgaysinh = new JLabel();
		lbSDT = new JLabel();
		lbDiachi = new JLabel();
		tfMaNV = new JTextField();
		tfHoten = new JTextField();
		tfSDT = new JTextField();
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		tfTaikhoan = new JTextField();
		pass = new JPasswordField();
		tfDiachi = new JTextField();
		rbNam = new JRadioButton();
		rbNu = new JRadioButton();
		jLabel3 = new JLabel();
		passConfirm = new JPasswordField();
		jLabel4 = new JLabel();
		tfNgaysinh = new JDateChooser();
		btnFind = new JButton();
		tfFind = new JTextField();
		jPanel2 = new JPanel();
		btnAdd = new JButton();
		btnEdit = new JButton();
		btnDel = new JButton();
		btnSave = new JButton();
		btnCancel = new JButton();
		btnBack = new JButton();
		lbTrangthai = new JLabel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Quản lý nhân viên");

		lbQLNV.setFont(new Font("Times New Roman", 0, 48));
		lbQLNV.setText("Quản lý nhân viên");

		tableNV.setFont(new Font("Tahoma", 0, 12));
		tableNV.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		tableNV.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableNVMouseClicked();
			}
		});
		jScrollPane1.setViewportView(tableNV);

		lbMaNV.setFont(new Font("Tahoma", 0, 14));
		lbMaNV.setText("Mã Nhân viên:");

		lbHoten.setFont(new Font("Tahoma", 0, 14));
		lbHoten.setText("Họ và Tên:");

		lbGioitinh.setFont(new Font("Tahoma", 0, 14));
		lbGioitinh.setText("Giới tính:");

		lbNgaysinh.setFont(new Font("Tahoma", 0, 14));
		lbNgaysinh.setText("Ngày sinh:");

		lbSDT.setFont(new Font("Tahoma", 0, 14));
		lbSDT.setText("Số điện thoại:");

		lbDiachi.setFont(new Font("Tahoma", 0, 14));
		lbDiachi.setText("Địa chỉ:");

		tfMaNV.setFont(new Font("Tahoma", 0, 14));

		tfHoten.setFont(new Font("Tahoma", 0, 12));

		tfSDT.setFont(new Font("Tahoma", 0, 14));
		tfSDT.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				tfSDTKeyReleased();
			}
		});

		jLabel1.setFont(new Font("Tahoma", 0, 14));
		jLabel1.setText("Tài khoản:");

		jLabel2.setFont(new Font("Tahoma", 0, 14));
		jLabel2.setText("Mật khẩu:");

		tfTaikhoan.setFont(new Font("Tahoma", 0, 14));

		pass.setFont(new Font("Tahoma", 0, 14));

		tfDiachi.setFont(new Font("Tahoma", 0, 14));

		buttonGroup1.add(rbNam);
		rbNam.setFont(new Font("Tahoma", 0, 14));
		rbNam.setText("Nam");

		buttonGroup1.add(rbNu);
		rbNu.setFont(new Font("Tahoma", 0, 14));
		rbNu.setText("Nữ");

		jLabel3.setFont(new Font("Tahoma", 0, 24));
		jLabel3.setText("Thông tin");

		passConfirm.setFont(new Font("Tahoma", 0, 14));

		jLabel4.setFont(new Font("Tahoma", 0, 14));
		tfNgaysinh.setDateFormatString("yyyy-MM-dd");

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addComponent(lbMaNV)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												tfMaNV, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE))
								.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
										.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup().addComponent(lbSDT).addGap(7, 7,
												7))
										.addGroup(GroupLayout.Alignment.TRAILING,
												jPanel1Layout.createSequentialGroup()
														.addGroup(jPanel1Layout
																.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addComponent(lbHoten, GroupLayout.Alignment.TRAILING)
																.addComponent(lbNgaysinh,
																		GroupLayout.Alignment.TRAILING)
																.addComponent(lbDiachi, GroupLayout.Alignment.TRAILING))
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
										.addGroup(
												jPanel1Layout
														.createParallelGroup(GroupLayout.Alignment.LEADING, false)
														.addComponent(tfSDT).addComponent(
																tfHoten)
														.addComponent(tfDiachi, GroupLayout.DEFAULT_SIZE, 127,
																Short.MAX_VALUE)
														.addComponent(tfNgaysinh, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout
														.createSequentialGroup().addGroup(jPanel1Layout
																.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addComponent(jLabel4, GroupLayout.Alignment.TRAILING)
																.addComponent(jLabel2, GroupLayout.Alignment.TRAILING,
																		GroupLayout.PREFERRED_SIZE, 65,
																		GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(jPanel1Layout
																.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addComponent(passConfirm, GroupLayout.PREFERRED_SIZE,
																		125, GroupLayout.PREFERRED_SIZE)
																.addComponent(pass, GroupLayout.PREFERRED_SIZE, 125,
																		GroupLayout.PREFERRED_SIZE))
														.addGap(1, 1, 1))
												.addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout
														.createSequentialGroup()
														.addGroup(jPanel1Layout
																.createParallelGroup(GroupLayout.Alignment.TRAILING)
																.addComponent(jLabel1).addComponent(lbGioitinh))
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(jPanel1Layout
																.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addGroup(jPanel1Layout.createSequentialGroup()
																		.addComponent(rbNu, GroupLayout.PREFERRED_SIZE,
																				54, GroupLayout.PREFERRED_SIZE)
																		.addGap(8, 8, 8).addComponent(rbNam,
																				GroupLayout.PREFERRED_SIZE, 63,
																				GroupLayout.PREFERRED_SIZE))
																.addComponent(tfTaikhoan, GroupLayout.PREFERRED_SIZE,
																		125, GroupLayout.PREFERRED_SIZE))))))
						.addContainerGap())
				.addGroup(GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel3).addGap(211, 211, 211)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel3)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(lbMaNV).addComponent(tfMaNV, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(lbHoten)
												.addComponent(tfHoten, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lbGioitinh).addComponent(rbNu).addComponent(rbNam))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(lbNgaysinh).addComponent(jLabel1).addComponent(tfTaikhoan,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)))
								.addComponent(tfNgaysinh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbSDT, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfSDT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbDiachi).addComponent(jLabel4)
								.addComponent(passConfirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(tfDiachi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		btnFind.setFont(new Font("Tahoma", 0, 14));
		btnFind.setIcon(new ImageIcon(getClass().getResource("/Photos/search (1).png"))); 
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnFindActionPerformed();
			}
		});

		tfFind.setFont(new Font("Tahoma", 0, 14));

		btnAdd.setFont(new Font("Tahoma", 0, 14));
		btnAdd.setIcon(new ImageIcon(getClass().getResource("/Photos/user (1).png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnAddActionPerformed();
			}
		});

		btnEdit.setFont(new Font("Tahoma", 0, 14));
		btnEdit.setIcon(new ImageIcon(getClass().getResource("/Photos/user(2).png")));
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnEditActionPerformed();
			}
		});

		btnDel.setFont(new Font("Tahoma", 0, 14));
		btnDel.setIcon(new ImageIcon(getClass().getResource("/Photos/user.png")));
		btnDel.setEnabled(false);
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnDelActionPerformed();
			}
		});

		btnSave.setFont(new Font("Tahoma", 0, 14));
		btnSave.setIcon(new ImageIcon(getClass().getResource("/Photos/floppy-disk (1).png")));
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSaveActionPerformed();
			}
		});

		btnCancel.setFont(new Font("Tahoma", 0, 14));
		btnCancel.setIcon(new ImageIcon(getClass().getResource("/Photos/x-button.png")));
		btnCancel.setEnabled(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnCancelActionPerformed();
			}
		});

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(btnDel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(btnCancel).addComponent(btnEdit).addComponent(btnAdd)
								.addComponent(btnSave).addComponent(btnDel))));

		btnBack.setFont(new Font("Tahoma", 0, 14));
		btnBack.setIcon(new ImageIcon(getClass().getResource("/Photos/smart-home.png")));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnBackActionPerformed();
			}
		});

		lbTrangthai.setFont(new Font("Times New Roman", 0, 14));
		lbTrangthai.setHorizontalAlignment(SwingConstants.CENTER);
		lbTrangthai.setText("Trạng Thái");

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(btnBack).addGap(259, 259, 259)
								.addComponent(lbQLNV).addGap(0, 0, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addGap(27, 27, 27).addGroup(layout
								.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup().addComponent(tfFind)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(btnFind, GroupLayout.PREFERRED_SIZE, 85,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 600,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(lbTrangthai, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jPanel1, GroupLayout.Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)))
										.addGroup(GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup()
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30,
																Short.MAX_VALUE)
														.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(19, 19, 19)))))
				.addContainerGap()));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addContainerGap()
														.addComponent(lbQLNV))
												.addComponent(btnBack))
										.addGap(18, 18, 18)
										.addGroup(layout
												.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
														.createSequentialGroup()
														.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 339,
																GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, Short.MAX_VALUE)
														.addGroup(layout
																.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addComponent(tfFind, GroupLayout.Alignment.TRAILING,
																		GroupLayout.PREFERRED_SIZE, 37,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(btnFind, GroupLayout.Alignment.TRAILING,
																		GroupLayout.PREFERRED_SIZE, 37,
																		GroupLayout.PREFERRED_SIZE)))
												.addGroup(layout.createSequentialGroup()
														.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(57, 57, 57)
														.addComponent(lbTrangthai, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGap(54, 54, 54).addComponent(jPanel2,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)))
										.addGap(28, 28, 28)));

		pack();
		try {
			Connect_DB.getInstance().connet();
			System.out.println("Connect QuanLiNhanVien Thành Công !!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void btnAddActionPerformed() {
		reset();
		add = true;
		Enabled();
		btnAdd.setEnabled(false);
		btnSave.setEnabled(true);
		btnCancel.setEnabled(true);
	}

	private void btnEditActionPerformed() {
		add = false;
		change = true;
		Enabled();
		btnAdd.setEnabled(false);
		btnDel.setEnabled(false);
		btnEdit.setEnabled(false);
		btnSave.setEnabled(true);
		btnCancel.setEnabled(true);
	}

	private void btnDelActionPerformed() {
		int click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nhân viên này hay không?", "Thông báo", 2);
		if (click == JOptionPane.YES_OPTION) {
			String maNV = tfMaNV.getText(); // Lấy mã nhân viên từ TextField
			try {
				nhanVienDAO.deleteNhanVien(maNV); // Gọi phương thức xóa nhân viên từ DAO
				reset();
				loadData();
				Disabled();
				lbTrangthai.setText("Xóa nhân viên thành công!");
			} catch (SQLException ex) {
				ex.printStackTrace();
				lbTrangthai.setText("Lỗi: Không thể xóa nhân viên!");
			}
		} else {
			reset();
		}
	}

	private void tableNVMouseClicked() {
		int click = tableNV.getSelectedRow();
		TableModel model = tableNV.getModel();

		tfMaNV.setText(model.getValueAt(click, 0).toString());
		tfHoten.setText(model.getValueAt(click, 1).toString());
		((JTextField) tfNgaysinh.getDateEditor().getUiComponent()).setText(model.getValueAt(click, 3).toString());
		tfSDT.setText(model.getValueAt(click, 4).toString());
		tfDiachi.setText(model.getValueAt(click, 5).toString());

		checkGT(model.getValueAt(click, 2).toString());
		loadAccount(model.getValueAt(click, 0).toString());

		btnEdit.setEnabled(true);
		btnDel.setEnabled(true);
		// checkDecentralization();
	}

	private void btnBackActionPerformed() {
		if (detail != null) { // Kiểm tra xem detail có bằng null không
			MainFr home = new MainFr(detail);
			this.setVisible(false);
			home.setVisible(true);
		} else {
			// Xử lý trường hợp detail là null, có thể hiển thị thông báo lỗi hoặc thực hiện
			// hành động khác tùy theo yêu cầu của ứng dụng.
			System.out.println("Lỗi: detail là null");
		}
	}

	private void btnSaveActionPerformed() {
		if (add == true) {
			if (check()) {
				addNV();
			}
		} else {
			if (change == true)
				changeNV();
		}
	}

	private void btnCancelActionPerformed() {
		reset();
		Disabled();
		loadData();
		// checkDecentralization();
	}

	private void tfSDTKeyReleased() {
		tfSDT.setText(cutChar(tfSDT.getText()));

		if (tfSDT.getText().length() == 11 || tfSDT.getText().length() == 10) {

			btnSave.setEnabled(true);
			lbTrangthai.setText("Số điện thoại đã hợp lệ!!");
		} else if (tfSDT.getText().length() > 11 || tfSDT.getText().length() < 10) {
			btnSave.setEnabled(false);
			lbTrangthai.setText("Số điện thoại không được nhỏ hơn 10 số hoặc vượt quá 11 số!!");
		}
	}

	private void btnFindActionPerformed() {
		String searchTerm = tfFind.getText();
		if (!searchTerm.isEmpty()) {
			try {
				List<NhanVien> searchResults = nhanVienDAO.searchNhanVien(searchTerm);
				displaySearchResults(searchResults);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			loadData();
		}
	}

	private void displaySearchResults(List<NhanVien> searchResults) {
		tableModel.setRowCount(0);
		for (NhanVien nhanVien : searchResults) {
			Object[] rowData = { nhanVien.getMaNV(), nhanVien.getTenNV(), nhanVien.getGioiTinh(),
					nhanVien.getNgaySinh(), nhanVien.getSdt(), nhanVien.getDiaChi(), nhanVien.getTaiKhoan(),
					nhanVien.getMatKhau() };
			tableModel.addRow(rowData);
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
			Logger.getLogger(qlnvFr.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(qlnvFr.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(qlnvFr.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(qlnvFr.class.getName()).log(Level.SEVERE, null, ex);
		}

		Connect_DB connectDB = Connect_DB.getInstance();
		connectDB.connet();
		// Kết nối với cơ sở dữ liệu
		NhanVienDAO nhanVienDAO = new NhanVienDAO(Connect_DB.getConnection()); // Tạo đối tượng NhanVienDAO với kết nối																		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				qlnvFr qlnvFrame = new qlnvFr(nhanVienDAO); // Truyền đối tượng NhanVienDAO vào constructor của qlnvFr
				qlnvFrame.setVisible(true);

			}
		});
	}
}
