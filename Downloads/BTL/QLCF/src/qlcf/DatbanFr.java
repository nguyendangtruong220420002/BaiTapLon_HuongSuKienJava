
package qlcf;

//import Database.MyDatabase;
//import Database.SQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DatbanFr extends javax.swing.JFrame {

    private Detail detail;
    private Connection conn=null;
    private ResultSet rs=null;
    
    private boolean add=false, change=false;
      
    private String sql="SELECT * FROM DatBan ORDER BY ban ASC";
    
  //  private MyDatabase SQL;
    
    public DatbanFr(Detail d) {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(this);
        detail=new Detail(d);
        Disabled();
      //  SQL=new MyDatabase(new SQL());
       // conn=SQL.connection("ThanhTrung", 1433, "QuanCaPhe", "sa", "sa2016");
        loadBan();
        Load(sql);
    }
    
    private String cutChar(String arry){
        return arry.replaceAll("\\D+","");
    }
    
    private void Load(String sql){
        try{
            String[] arry={"Tên khách hàng","SĐT","Bàn","Thời gian","Ngày","Thanh toán","Ghi Chú"};
            DefaultTableModel model=new DefaultTableModel(arry,0);
            
          //  rs = SQL.excuteQuery(conn, sql);
            
            while(rs.next()){
                Vector vector=new Vector();
                vector.add(rs.getString("tenKH").trim());
                vector.add(rs.getString("sdt").trim());
                vector.add(rs.getInt("ban"));
                vector.add(rs.getString("thoiGian").trim());
                vector.add(rs.getString("ngay").trim());
                vector.add(rs.getString("thanhToan").trim());
                vector.add(rs.getString("ghiChu").trim());
                model.addRow(vector);
            }
            tableDatban.setModel(model);
          //  SQL.closeResultSet(rs);
        }
        catch(Exception ex){
            ex.printStackTrace();
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
       // ((JTextField)tfDay.getDateEditor().getUiComponent()).setText("");
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
    
    private boolean checkNull(){
        if(tfTenkhach.getText().equals(""))
        {
         lbTrangthai.setText("Bạn chưa nhập tên khách hàng");
         return false;
        }else
         if(tfSDT.getText().equals("")){
            lbTrangthai.setText("Bạn cần phải nhập số điện thoại của khách");
            return false;
        }else
         if(cboSoban.getSelectedItem()== null){
            lbTrangthai.setText("Bạn cần chọn bàn cho khách");
            return false;
        }
//        else
//         if(tfTimes.getText().equals("")){
//            lbTrangthai.setText("Chưa có thời gian đặt bàn của khách");
//            return false;
//        }else
//       if(((JTextField)tfDay.getDateEditor().getUiComponent()).getText().equals("")){
//            lbTrangthai.setText("Chưa ấn định ngày đặt bàn");
//            return false;
//        }
       else
         if(radNo.isSelected()==false && radDathanhtoan.isSelected()==false){
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
     //   tfDay.setEnabled(true);
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
      //  tfDay.setEnabled(false);
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
    
//    private void addCustomer(){
//        if(checkNull()){
//            String sqladd = "INSERT INTO DatBan (tenKH,sdt,ban,thoiGian,ngay,thanhToan,ghiChu) VALUES (N'"+tfTenkhach.getText()+"',N'"+tfSDT.getText()+"',"+cboSoban.getSelectedItem()+",'"+cbxHours.getSelectedItem()+lbl2Cham.getText()+cbxMinute.getSelectedItem()+"','"+((JTextField)tfDay.getDateEditor().getUiComponent()).getText()+"','"+thanhtoan()+"',N'"+this.tfNote.getText()+"')";
//            
//            
//            SQL.excuteUpdata(conn, sqladd);
//            
//            lbTrangthai.setText("Đã thêm thành công khách hàng " +tfTenkhach.getText());
//            reset();
//            Load(sql);
//            Disabled();
//            
//        }
//    }
    
//    private void editCustomer(){
//        if(checkNull()){
//            int click=tableDatban.getSelectedRow();
//            TableModel model=tableDatban.getModel();
//        
//            String sqlChange="UPDATE DatBan SET tenKH=N'"+tfTenkhach.getText()+"', sdt='"+tfSDT.getText()+"', thanhToan=N'"+thanhtoan()+"',ban="+cboSoban.getSelectedItem()+", thoiGian='"+cbxHours.getSelectedItem()+lbl2Cham.getText()+cbxMinute.getSelectedItem()+"', ngay='"+((JTextField)tfDay.getDateEditor().getUiComponent()).getText()+"', ghiChu=N'"+this.tfNote.getText()+"' WHERE sdt=N'"+model.getValueAt(click, 1)+"'";
//            
//            SQL.excuteUpdata(conn, sqlChange);
//            
//            reset();
//            Load(sql);
//            Disabled();
//            lbTrangthai.setText("Sửa thông tin khách hàng thành công!");
//        }
//    }
    
    private boolean check(){
        try {
            
          //  rs=SQL.excuteQuery( conn, sql);
            
            while(rs.next()){
                if(rs.getString("sdt").toString().trim().equals(tfSDT.getText())){
                    lbTrangthai.setText("Khách hàng đã tồn tại");
                    return false;
                }  
            }
          //  SQL.closeResultSet( rs);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbTenkhach = new javax.swing.JLabel();
        lbSoban = new javax.swing.JLabel();
        lbTime = new javax.swing.JLabel();
        lbDay = new javax.swing.JLabel();
        tfTenkhach = new javax.swing.JTextField();
        cboSoban = new javax.swing.JComboBox<>();
        tfSDT = new javax.swing.JTextField();
        lbSDT = new javax.swing.JLabel();
        lbThanhtoan = new javax.swing.JLabel();
        radNo = new javax.swing.JRadioButton();
        radDathanhtoan = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        tfNote = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDatban = new javax.swing.JTable();
      //  tfDay = new com.toedter.calendar.JDateChooser();
        cbxHours = new javax.swing.JComboBox<>();
        cbxMinute = new javax.swing.JComboBox<>();
        lbl2Cham = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        lbTrangthai = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đặt bàn");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        jLabel1.setText("ĐẶT BÀN");

        lbTenkhach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTenkhach.setText("Tên khách hàng:");

        lbSoban.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSoban.setText("Bàn số:");

        lbTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTime.setText("Thời gian:");

        lbDay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbDay.setText("Ngày:");

        tfTenkhach.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cboSoban.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tfSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSDTKeyReleased(evt);
            }
        });

        lbSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSDT.setText("Số điện thoại:");

        lbThanhtoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbThanhtoan.setText("Trả trước:");

        radNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radNo.setText("Không");
        radNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radNoActionPerformed(evt);
            }
        });

        radDathanhtoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radDathanhtoan.setText("Đã thanh toán");
        radDathanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDathanhtoanActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ghi chú", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        tfNote.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfNote, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(tfNote)
                .addContainerGap())
        );

        tableDatban.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDatban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDatbanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDatban);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

      //  tfDay.setDateFormatString("dd/MM/yyyy");

        cbxHours.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cbxMinute.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbl2Cham.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl2Cham.setText(":");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbThanhtoan)
                            .addComponent(lbSDT)
                            .addComponent(lbSoban)
                            .addComponent(lbTenkhach)
                            .addComponent(lbTime)
                            .addComponent(lbDay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfSDT)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(radNo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radDathanhtoan))
                            .addComponent(cboSoban, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfTenkhach)
                           // .addComponent(tfDay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbxHours, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl2Cham, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxMinute, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTenkhach)
                            .addComponent(tfTenkhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSDT)
                            .addComponent(tfSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSoban)
                            .addComponent(cboSoban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTime)
                            .addComponent(cbxHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl2Cham))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDay)
                            //.addComponent(tfDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            )
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbThanhtoan)
                            .addComponent(radNo)
                            .addComponent(radDathanhtoan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/floppy-disk (1).png"))); // NOI18N
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              //  btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/x-button.png"))); // NOI18N
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/plus.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/edit.png"))); // NOI18N
        btnEdit.setEnabled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/delete.png"))); // NOI18N
        btnDel.setEnabled(false);
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnHome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/smart-home.png"))); // NOI18N
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        lbTrangthai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTrangthai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTrangthai.setText("Trạng thái");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbTrangthai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnHome)
                                .addGap(350, 350, 350)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnHome)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTrangthai)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        reset();
        add=true;
        Enabled();
        
        btnSave.setEnabled(true);   
        btnAdd.setEnabled(false);
        btnCancel.setEnabled(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void radNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNoActionPerformed
        radNo.setSelected(true);
        radDathanhtoan.setSelected(false);
    }//GEN-LAST:event_radNoActionPerformed

    private void radDathanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDathanhtoanActionPerformed
        radDathanhtoan.setSelected(true);
        radNo.setSelected(false);
    }//GEN-LAST:event_radDathanhtoanActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
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
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        btnSave.setEnabled(true); 
        int click=JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa khách hàng này không?", "Thông báo", 2);
        if(click==JOptionPane.YES_OPTION){
            
            String sqlDelete="DELETE FROM DatBan WHERE sdt=N'"+tfSDT.getText()+"'";
            
          //  SQL.excuteUpdata( conn, sqlDelete);
            
            reset();
            Load(sql);
            Disabled();
            lbTrangthai.setText("Xóa thành công khách hàng");
        }
        else reset();
    }//GEN-LAST:event_btnDelActionPerformed

//    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
//
//        if(add==true){
//            if(check()){
//                addCustomer();
//            }
//        }
//        else{
//            if(change==true)
//                editCustomer();
//        }
//    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        MainFr home = new MainFr(detail);
        this.setVisible(false);
        home.setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void tableDatbanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDatbanMouseClicked
        cboSoban.removeAllItems();
        cbxHours.removeAllItems();
        cbxMinute.removeAllItems();
        
        int click=tableDatban.getSelectedRow();
        TableModel model=tableDatban.getModel();
        
        tfTenkhach.setText(model.getValueAt(click, 0).toString());
        tfSDT.setText(model.getValueAt(click, 1).toString());
        cboSoban.addItem(model.getValueAt(click, 2).toString());
        
        String[] s=model.getValueAt(click, 3).toString().split(":");
        
        cbxHours.addItem(s[0]);
        cbxMinute.addItem(s[1]);
        
       // ((JTextField)tfDay.getDateEditor().getUiComponent()).setText(model.getValueAt(click, 4).toString());
        tfNote.setText(model.getValueAt(click, 6).toString());
        checkThanhtoan(model.getValueAt(click, 5).toString());
        
        
        btnEdit.setEnabled(true);
        btnDel.setEnabled(true);
    }//GEN-LAST:event_tableDatbanMouseClicked

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        reset();
        Disabled();
        Load(sql);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tfSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSDTKeyReleased
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
    }//GEN-LAST:event_tfSDTKeyReleased

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DatbanFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatbanFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatbanFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatbanFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Detail detail= new Detail();
                new DatbanFr(detail).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cboSoban;
    private javax.swing.JComboBox<String> cbxHours;
    private javax.swing.JComboBox<String> cbxMinute;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDay;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbSoban;
    private javax.swing.JLabel lbTenkhach;
    private javax.swing.JLabel lbThanhtoan;
    private javax.swing.JLabel lbTime;
    private javax.swing.JLabel lbTrangthai;
    private javax.swing.JLabel lbl2Cham;
    private javax.swing.JRadioButton radDathanhtoan;
    private javax.swing.JRadioButton radNo;
    private javax.swing.JTable tableDatban;
  //  private com.toedter.calendar.JDateChooser tfDay;
    private javax.swing.JTextField tfNote;
    private javax.swing.JTextField tfSDT;
    private javax.swing.JTextField tfTenkhach;
    // End of variables declaration//GEN-END:variables
}
