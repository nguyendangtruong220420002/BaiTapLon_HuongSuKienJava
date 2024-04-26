
package qlcf;


import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class qlnuocFr extends javax.swing.JFrame {

    private Detail detail;
    
    private String sql="SELECT * FROM QLNuoc ORDER BY maNuoc";
    
    private Connection conn=null;
    private ResultSet rs=null;
   // private MyDatabase SQL;
    
    private boolean add=false, change=false;
    
    public qlnuocFr(Detail d) {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(this);
        detail=new Detail(d);
        
        Disabled();
        loadData(sql);
        loadLoaiNuoc();
    }
    
    private void checkKyTu(String arry){
        char[] character=arry.toCharArray();
        for(int i = 0; i<character.length;i++){
            if(String.valueOf(character[i]).matches("\\D+")){
                btnSave.setEnabled(false);
                lbTrangthai.setText("Số lượng không thể chứa kí tự");
                break;
            }
            else btnSave.setEnabled(true);
        }
    }
    
    private String cutChar(String arry){
        return arry.replaceAll("\\D+","");
    }
    
    private String cutNumber(String arry){
        return arry.replaceAll("\\d+","");
    }
    
    private void loadLoaiNuoc(){
        cbLoaiNuoc.removeAllItems();
        cbLoaiNuoc.addItem("Cafe");
        cbLoaiNuoc.addItem("Sinh Tố");
        cbLoaiNuoc.addItem("Nước Giải Khát");
    }
    
    private void loadData(String sql){
        try{
            String[] arry={"Mã Thức Uống","Loại Nước","Tên Nước","Đơn Vị","Số Lượng","Giá Bán"};
            DefaultTableModel model=new DefaultTableModel(arry,0);
            
            
            while(rs.next()){
                Vector vector=new Vector();
                vector.add(rs.getString("maNuoc").trim());
                vector.add(rs.getString("loaiNuoc").trim());
                vector.add(rs.getString("tenNuoc").trim());
                vector.add(rs.getString("donVi").trim());
                vector.add(rs.getInt("soLuong"));
                vector.add(rs.getString("giaBan").trim());
                
                model.addRow(vector);
            }
            tableDrink.setModel(model);
          //  SQL.closeResultSet( rs);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private double convertedToNumbers(String s){
        String number="";
        String []array=s.replace(","," ").split("\\s");
        for(String i:array){
            number=number.concat(i);
        }
        return Double.parseDouble(number);
    }
    
    private void Enabled(){
        tfMatu.setEnabled(true);
        cbLoaiNuoc.setEnabled(true);
        tfTen.setEnabled(true);
        tfDonVi.setEnabled(true);
        tfSoLuong.setEnabled(true);
        tfGia.setEnabled(true);
    }
    
    private void Disabled(){
        tfMatu.setEnabled(false);
        cbLoaiNuoc.setEnabled(false);
        tfTen.setEnabled(false);
        tfDonVi.setEnabled(false);
        tfSoLuong.setEnabled(false);
        tfGia.setEnabled(false);
        
    }
    
    private void reset(){
        add=false;
        change=false;
        loadLoaiNuoc();
        tfMatu.setText("");
        cbLoaiNuoc.setSelectedIndex(0);
        tfTen.setText("");
        tfDonVi.setText("");
        tfSoLuong.setText("");
        tfGia.setText("");
        lbTrangthai.setText("Trạng Thái");
        btnAdd.setEnabled(true);
        btnSave.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDel.setEnabled(false);
        btnCancel.setEnabled(false);
    }

    private boolean checkNull(){
        if(tfMatu.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập mã thức uống!");
            return false;
        }
        else
        if(cbLoaiNuoc.getSelectedItem().equals("")){
            lbTrangthai.setText("Bạn chưa chọn loại thức uống!");
            return false;
        }
        else
        if(tfTen.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập tên thức uống");
            return false;
        }
        else   
        if(tfDonVi.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập đơn vị tính!");
            return false;
        }
        else   
        if(tfSoLuong.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập số lượng nước!");
            return false;
        }
        else   
        if(tfGia.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập giá!");
            return false;
        }
        return true;
    }
    
    private void addDrink(){
        if(checkNull()){
            String sqlAdd="INSERT INTO QLNuoc (maNuoc,loaiNuoc,tenNuoc,giaBan,donVi,soLuong) VALUES (N'"+tfMatu.getText()+"',N'"+cbLoaiNuoc.getSelectedItem().toString()+"',N'"+tfTen.getText()+"',N'"+(tfGia.getText()+" "+"VNĐ")+"',N'"+tfDonVi.getText()+"',"+tfSoLuong.getText()+")";
            
          //  SQL.excuteUpdata(conn, sqlAdd);
            
            reset();
            loadData(sql);
            Disabled();
            lbTrangthai.setText("Thêm thức uống thành công!");
        }
    }
    
    private void editDrink(){
         if(checkNull()){
            int click=tableDrink.getSelectedRow();
            TableModel model=tableDrink.getModel();
        
            String sqlChange="UPDATE QLNuoc SET maNuoc='"+tfMatu.getText()+"', loaiNuoc=N'"+cbLoaiNuoc.getSelectedItem()+"', tenNuoc=N'"+tfTen.getText()+"', giaBan='"+(tfGia.getText()+" "+"VNĐ")+"', donVi='"+tfDonVi.getText()+"',soLuong="+tfSoLuong.getText()+" WHERE maNuoc=N'"+model.getValueAt(click, 0)+"'";
   
           // SQL.excuteUpdata(conn, sqlChange);
           // SQL.closeResultSet(rs);
            reset();
            loadData(sql);
            Disabled();
            lbTrangthai.setText("Thay đổi thông tin thức uống thành công");
        }
     }
    
    private boolean check(){
        try {
            
          //  rs=SQL.excuteQuery( conn, sql);
            
            while(rs.next()){
                if(rs.getString("maNuoc").toString().trim().equals(tfMatu.getText())){
                    lbTrangthai.setText("Mã nước bạn nhập đã tồn tại");
                    return false;
                }
                else
                if(rs.getString("tenNuoc").toString().trim().equals(tfTen.getText())){
                    lbTrangthai.setText("Thức uống bạn nhập đã tồn tại");
                    return false;
                }
            }
           // SQL.closeResultSet( rs);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbQLTU = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbMatu = new javax.swing.JLabel();
        lbLoai = new javax.swing.JLabel();
        lbTen = new javax.swing.JLabel();
        lbGia = new javax.swing.JLabel();
        tfMatu = new javax.swing.JTextField();
        tfTen = new javax.swing.JTextField();
        tfGia = new javax.swing.JTextField();
        cbLoaiNuoc = new javax.swing.JComboBox<>();
        lbThongtin = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfDonVi = new javax.swing.JTextField();
        tfSoLuong = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDrink = new javax.swing.JTable();
        tfFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lbTrangthai = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý thức uống");

        lbQLTU.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbQLTU.setText("Quản lý thức uống");

        lbMatu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbMatu.setText("Mã thức uống:");

        lbLoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbLoai.setText("Loại thức uống:");

        lbTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTen.setText("Tên thức uống:");

        lbGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbGia.setText("Giá tiền:");

        tfMatu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tfTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tfGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfGiaKeyReleased(evt);
            }
        });

        cbLoaiNuoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbThongtin.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbThongtin.setText("Thông tin Thức uống");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Đơn vị tính:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Số lượng:");

        tfDonVi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tfSoLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSoLuongKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(lbThongtin))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbLoai)
                            .addComponent(lbMatu)
                            .addComponent(lbTen)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(lbGia))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbLoaiNuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfMatu)
                            .addComponent(tfDonVi)
                            .addComponent(tfSoLuong)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbThongtin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMatu)
                    .addComponent(tfMatu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLoai)
                    .addComponent(cbLoaiNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTen)
                    .addComponent(tfTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbGia)
                    .addComponent(tfGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableDrink.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableDrink.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã thức uống", "Loại thức uống", "Tên thức uống", "Giá bán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableDrink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDrinkMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDrink);
        if (tableDrink.getColumnModel().getColumnCount() > 0) {
            tableDrink.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableDrink.getColumnModel().getColumn(1).setPreferredWidth(25);
            tableDrink.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        tfFind.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnFind.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/search (1).png"))); // NOI18N
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/smart-home.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/plus.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/edit.png"))); // NOI18N
        btnEdit.setEnabled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/delete.png"))); // NOI18N
        btnDel.setEnabled(false);
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/floppy-disk.png"))); // NOI18N
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSave)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lbTrangthai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTrangthai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTrangthai.setText("Trạng thái");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbQLTU)
                .addGap(246, 246, 246))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfFind, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)))
                        .addGap(13, 13, 13))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbQLTU))
                    .addComponent(btnBack))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(tfFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnFind)
                                .addGap(27, 27, 27))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        reset();
        add=true;
        Enabled();
        btnAdd.setEnabled(false);
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
    }//GEN-LAST:event_btnAddActionPerformed
    
    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int click=JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thức uống này hay không?", "Thông báo", 2);
        if(click==JOptionPane.YES_OPTION){
            
            String sqlDelete="DELETE FROM QLNuoc WHERE maNuoc='"+tfMatu.getText()+"'";
            
           // SQL.excuteUpdata( conn, sqlDelete);
            
            reset();
            loadData(sql);
            Disabled();
            lbTrangthai.setText("Xóa thành công thức uống!");
        }
        else reset();
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        add=false;
        change=true;
        Enabled();
        btnAdd.setEnabled(false);
        btnDel.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        Disabled();
        reset();
        loadData(sql);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        MainFr home = new MainFr(detail);
        this.setVisible(false);
        home.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void tableDrinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDrinkMouseClicked
        cbLoaiNuoc.removeAllItems();
        int click=tableDrink.getSelectedRow();
        TableModel model=tableDrink.getModel();
        
        tfMatu.setText(model.getValueAt(click, 0).toString());
        cbLoaiNuoc.addItem(model.getValueAt(click, 1).toString());
        tfTen.setText(model.getValueAt(click, 2).toString());
        tfDonVi.setText(model.getValueAt(click, 3).toString());
        tfSoLuong.setText(model.getValueAt(click, 4).toString());
        
        String []s1=model.getValueAt(click,5).toString().split("\\s");
        tfGia.setText(s1[0]);
        
        //tfGia.setText(model.getValueAt(click, 5).toString());
        
        this.btnEdit.setEnabled(true);
        this.btnDel.setEnabled(true);
    }//GEN-LAST:event_tableDrinkMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(add==true){
            if(check()){
                addDrink();
            }
        }
        else{
            if(change==true)
                editDrink();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        String sql = "SELECT * FROM QLNuoc where maNuoc like N'%"+tfFind.getText()+"%' or tenNuoc like N'%"+tfFind.getText()+"%' or loaiNuoc like N'%"+tfFind.getText()+"%'";
        loadData(sql);
        tfFind.setText("");
        Disabled();
        reset();
        btnCancel.setEnabled(true);
    }//GEN-LAST:event_btnFindActionPerformed

    private void tfGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfGiaKeyReleased
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        
        if(tfGia.getText().equals("")){
            return;
        }
        else
        if(tfGia.getText().matches("\\D+")){
            tfGia.setText(cutChar(tfGia.getText()));
        }
        else{
            tfGia.setText(formatter.format(convertedToNumbers(cutChar(tfGia.getText()))));
        }
    }//GEN-LAST:event_tfGiaKeyReleased

    private void tfSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSoLuongKeyReleased
        //checkKyTu(this.tfSoLuong.getText());
        tfSoLuong.setText(cutChar(tfSoLuong.getText()));
    }//GEN-LAST:event_tfSoLuongKeyReleased

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
            java.util.logging.Logger.getLogger(qlnuocFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(qlnuocFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(qlnuocFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(qlnuocFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Detail detail= new Detail();
                new qlnuocFr(detail).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbLoaiNuoc;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbGia;
    private javax.swing.JLabel lbLoai;
    private javax.swing.JLabel lbMatu;
    private javax.swing.JLabel lbQLTU;
    private javax.swing.JLabel lbTen;
    private javax.swing.JLabel lbThongtin;
    private javax.swing.JLabel lbTrangthai;
    private javax.swing.JTable tableDrink;
    private javax.swing.JTextField tfDonVi;
    private javax.swing.JTextField tfFind;
    private javax.swing.JTextField tfGia;
    private javax.swing.JTextField tfMatu;
    private javax.swing.JTextField tfSoLuong;
    private javax.swing.JTextField tfTen;
    // End of variables declaration//GEN-END:variables
}
