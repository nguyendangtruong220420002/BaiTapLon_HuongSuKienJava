//package qlcf;
//
//import java.sql.SQLException;
//
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//
//import connectBD.Connect_DB;
//import dao.NhanVienDAO;
//import dao.Nuoc_Dao;
//
//public class MainFr extends JFrame {
//
//    private static final long serialVersionUID = 1L;
//	private Detail detail;
//    
//    
//    public MainFr(Detail d ) {
//        initComponents();
//        setResizable(false);
//        setLocationRelativeTo(this);
//        detail=new Detail(d);
//    }
//
//    private void initComponents() {
//
//        jLabel3 = new javax.swing.JLabel();
//        jPanel1 = new javax.swing.JPanel();
//        btnQLNuoc = new javax.swing.JButton();
//        btnQLNV = new javax.swing.JButton();
//        btnBanhang = new javax.swing.JButton();
//        btnThongke = new javax.swing.JButton();
//        btnDatban = new javax.swing.JButton();
//        btnAbout = new javax.swing.JButton();
//        btnLogout = new javax.swing.JButton();
//        jLabel1 = new javax.swing.JLabel();
//        jLabel2 = new javax.swing.JLabel();
//        jLabel4 = new javax.swing.JLabel();
//        jLabel5 = new javax.swing.JLabel();
//        jLabel6 = new javax.swing.JLabel();
//        jLabel7 = new javax.swing.JLabel();
//        jLabel8 = new javax.swing.JLabel();
//        jLabel9 = new javax.swing.JLabel();
//        btnExit = new javax.swing.JButton();
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//
//        jLabel3.setFont(new java.awt.Font("Edwardian Script ITC", 0, 48)); // NOI18N
//        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        jLabel3.setText("Quán Cafe");
//        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
//
//        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
//
//        btnQLNuoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
//        btnQLNuoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/coffee (1)_(1).png"))); // NOI18N
//        btnQLNuoc.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                try {
//					btnQLNuocActionPerformed(evt);
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            }
//        });
//
//        btnQLNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/teamwork.png"))); // NOI18N
//        btnQLNV.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                try {
//					btnQLNVActionPerformed(evt);
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            }
//        });
//
//        btnBanhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/cafe.png"))); // NOI18N
//        btnBanhang.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                btnBanhangActionPerformed(evt);
//            }
//        });
//
//        btnThongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/graph.png"))); // NOI18N
//        btnThongke.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                btnThongkeActionPerformed(evt);
//            }
//        });
//
//        btnDatban.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/coffee-table (1).png"))); // NOI18N
//        btnDatban.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                btnDatbanActionPerformed(evt);
//            }
//        });
//
//        btnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/info.png"))); // NOI18N
//        btnAbout.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                btnAboutActionPerformed(evt);
//            }
//        });
//
//        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/logout.png"))); // NOI18N
//        btnLogout.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                btnLogoutActionPerformed(evt);
//            }
//        });
//
//        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
//        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        jLabel1.setText("Quản lý thức uống");
//
//        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
//        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        jLabel2.setText("Quản lý nhân viên");
//
//        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
//        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        jLabel4.setText("Bán hàng");
//
//        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
//        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        jLabel5.setText("Đặt bàn");
//
//        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
//        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        jLabel6.setText("Thống kê");
//
//        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
//        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        jLabel7.setText("Thông tin");
//
//        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
//        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        jLabel8.setText("Đăng xuất");
//
//        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
//        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        jLabel9.setText("Thoát");
//        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
//
//        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/exit.png"))); // NOI18N
//        btnExit.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                btnExitActionPerformed(evt);
//            }
//        });
//
//        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
//        jPanel1.setLayout(jPanel1Layout);
//        jPanel1Layout.setHorizontalGroup(
//            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(jPanel1Layout.createSequentialGroup()
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(jPanel1Layout.createSequentialGroup()
//                        .addGap(18, 18, 18)
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
//                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
//                                .addComponent(btnQLNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(18, 18, 18)
//                                .addComponent(btnQLNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
//                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addGap(18, 18, 18)
//                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
//                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
//                        .addContainerGap()
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                            .addComponent(btnAbout, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
//                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
//                .addGap(18, 18, 18)
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                    .addGroup(jPanel1Layout.createSequentialGroup()
//                        .addComponent(btnBanhang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addGap(18, 18, 18)
//                        .addComponent(btnDatban, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
//                    .addGroup(jPanel1Layout.createSequentialGroup()
//                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addGap(18, 18, 18)
//                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
//                    .addGroup(jPanel1Layout.createSequentialGroup()
//                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addGap(18, 18, 18)
//                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                    .addGroup(jPanel1Layout.createSequentialGroup()
//                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addGap(18, 18, 18)
//                        .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
//                .addGap(18, 18, 18)
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(btnThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addContainerGap(21, Short.MAX_VALUE))
//        );
//        jPanel1Layout.setVerticalGroup(
//            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(jPanel1Layout.createSequentialGroup()
//                .addGap(39, 39, 39)
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
//                        .addComponent(btnQLNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addComponent(btnQLNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
//                        .addComponent(btnThongke, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
//                    .addComponent(btnBanhang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
//                    .addComponent(btnDatban, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addComponent(jLabel1)
//                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                        .addComponent(jLabel4)
//                        .addComponent(jLabel2)
//                        .addComponent(jLabel5)
//                        .addComponent(jLabel6)))
//                .addGap(33, 33, 33)
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
//                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
//                    .addComponent(btnAbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                    .addComponent(jLabel7)
//                    .addComponent(jLabel8)
//                    .addComponent(jLabel9))
//                .addContainerGap(25, Short.MAX_VALUE))
//        );
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(layout.createSequentialGroup()
//                        .addGap(160, 160, 160)
//                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
//                    .addGroup(layout.createSequentialGroup()
//                        .addGap(97, 97, 97)
//                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                .addContainerGap(98, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(38, 38, 38)
//                .addComponent(jLabel3)
//                .addGap(31, 31, 31)
//                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(98, Short.MAX_VALUE))
//        );
//
//        pack();
//    }
//
//    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
//        int click=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình hay không?", "Thông báo", 2);
//        if(click==JOptionPane.YES_OPTION){
//            System.exit(0);
//        }
//    }//GEN-LAST:event_btnExitActionPerformed
//
//    private void btnQLNuocActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_btnQLNuocActionPerformed
//    	Connect_DB connectDB = Connect_DB.getInstance();
//        connectDB.connet();
//		Nuoc_Dao nuoc_Dao = new Nuoc_Dao(connectDB.getConnection());;
//       // qlnuocFr qln = new qlnuocFr(detail);
//		qlnuocFr qln = new qlnuocFr(nuoc_Dao);
//        this.setVisible(false);
//        qln.setVisible(true);
//    }//GEN-LAST:event_btnQLNuocActionPerformed
//
//    private void btnQLNVActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_btnQLNVActionPerformed
//    	Connect_DB connectDB = Connect_DB.getInstance();
//        connectDB.connet();
//		NhanVienDAO nhanVienDAO = new NhanVienDAO(connectDB.getConnection());;
//		//qlnvFr qlnv = new qlnvFr(detail);
//        qlnvFr qlnv = new qlnvFr(nhanVienDAO);
//        this.setVisible(false);
//        qlnv.setVisible(true);
//    }//GEN-LAST:event_btnQLNVActionPerformed
//
//    private void btnBanhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanhangActionPerformed
//        banhangFr banhang = new banhangFr(detail);
//        this.setVisible(false);
//        banhang.setVisible(true);
//    }
//
//    private void btnDatbanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatbanActionPerformed
//        DatbanFr datban = new DatbanFr(detail);
//        this.setVisible(false);
//        datban.setVisible(true);
//    }
//
//    private void btnThongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongkeActionPerformed
//        ThongkeFr thongke = new ThongkeFr(detail);
//        this.setVisible(false);
//        thongke.setVisible(true);
//    }
//
//    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutActionPerformed
//        AboutFr thongtin = new AboutFr(detail);
//        this.setVisible(false);
//        thongtin.setVisible(true);
//    }
//
//    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
//        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất khỏi tài khoản hay không?", "Thông Báo",2);
//        if(Click ==JOptionPane.YES_OPTION){
//            LoginFr dangnhap = new LoginFr();
//            this.setVisible(false);
//            dangnhap.setVisible(true);
//        }
//    }
//
//    public static void main(String args[]) {
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                Detail detail= new Detail();
//                new MainFr(detail).setVisible(true);
//            }
//        });
//    }
//
//    private javax.swing.JButton btnAbout;
//    private javax.swing.JButton btnBanhang;
//    private javax.swing.JButton btnDatban;
//    private javax.swing.JButton btnExit;
//    private javax.swing.JButton btnLogout;
//    private javax.swing.JButton btnQLNV;
//    private javax.swing.JButton btnQLNuoc;
//    private javax.swing.JButton btnThongke;
//    private javax.swing.JLabel jLabel1;
//    private javax.swing.JLabel jLabel2;
//    private javax.swing.JLabel jLabel3;
//    private javax.swing.JLabel jLabel4;
//    private javax.swing.JLabel jLabel5;
//    private javax.swing.JLabel jLabel6;
//    private javax.swing.JLabel jLabel7;
//    private javax.swing.JLabel jLabel8;
//    private javax.swing.JLabel jLabel9;
//    private javax.swing.JPanel jPanel1;
//}
package qlcf;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import connectBD.Connect_DB;
import dao.BanDAO;
import dao.DatBanDAO;
import dao.NhanVienDAO;
import dao.Nuoc_Dao;

public class MainFr extends JFrame {

    private static final long serialVersionUID = 1L;
    private Detail detail;
    
    private JButton btnAbout;
    private JButton btnBanhang;
    private JButton btnDatban;
    private JButton btnExit;
    private JButton btnLogout;
    private JButton btnQLNV;
    private JButton btnQLNuoc;
    private JButton btnThongke;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    
    
    public MainFr(Detail d ) {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(this);
        detail=new Detail(d);
    }

    private void initComponents() {

        jLabel3 = new JLabel();
        jPanel1 = new JPanel();
        btnQLNuoc = new JButton();
        btnQLNV = new JButton();
        btnBanhang = new JButton();
        btnThongke = new JButton();
        btnDatban = new JButton();
        btnAbout = new JButton();
        btnLogout = new JButton();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        btnExit = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new Font("Edwardian Script ITC", 0, 48)); 
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setText("Quán Cafe");
        jLabel3.setHorizontalTextPosition(SwingConstants.CENTER);

        jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        btnQLNuoc.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        btnQLNuoc.setIcon(new ImageIcon(getClass().getResource("/Photos/coffee (1)_(1).png"))); 
        btnQLNuoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btnQLNuocActionPerformed(evt);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        btnQLNV.setIcon(new ImageIcon(getClass().getResource("/Photos/teamwork.png"))); 
        btnQLNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btnQLNVActionPerformed(evt);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        btnBanhang.setIcon(new ImageIcon(getClass().getResource("/Photos/cafe.png"))); 
        btnBanhang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
					btnBanhangActionPerformed(evt);
				
            }
        });

        btnThongke.setIcon(new ImageIcon(getClass().getResource("/Photos/graph.png"))); 
        btnThongke.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnThongkeActionPerformed(evt);
            }
        });

        btnDatban.setIcon(new ImageIcon(getClass().getResource("/Photos/coffee-table (1).png"))); 
        btnDatban.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
					btnDatbanActionPerformed(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        btnAbout.setIcon(new ImageIcon(getClass().getResource("/Photos/info.png"))); 
        btnAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAboutActionPerformed(evt);
            }
        });

        btnLogout.setIcon(new ImageIcon(getClass().getResource("/Photos/logout.png"))); 
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jLabel1.setFont(new Font("Tahoma", 0, 14)); 
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Quản lý thức uống");

        jLabel2.setFont(new Font("Tahoma", 0, 14)); 
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("Quản lý nhân viên");

        jLabel4.setFont(new Font("Tahoma", 0, 14)); 
        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setText("Bán hàng");

        jLabel5.setFont(new Font("Tahoma", 0, 14)); 
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setText("Đặt bàn");

        jLabel6.setFont(new Font("Tahoma", 0, 14)); 
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setText("Thống kê");

        jLabel7.setFont(new Font("Tahoma", 0, 14)); 
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setText("Thông tin");

        jLabel8.setFont(new Font("Tahoma", 0, 14)); 
        jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel8.setText("Đăng xuất");

        jLabel9.setFont(new Font("Tahoma", 0, 14)); 
        jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel9.setText("Thoát");
        jLabel9.setHorizontalTextPosition(SwingConstants.CENTER);

        btnExit.setIcon(new ImageIcon(getClass().getResource("/Photos/exit.png"))); 
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnQLNuoc, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnQLNV, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))))
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAbout, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBanhang, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDatban, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThongke, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(btnQLNuoc, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnQLNV, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThongke, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBanhang, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDatban, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnAbout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addGap(31, 31, 31)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnExitActionPerformed(ActionEvent evt) {
        int click=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình hay không?", "Thông báo", 2);
        if(click==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    private void btnQLNuocActionPerformed(ActionEvent evt) throws SQLException {
        Connect_DB connectDB = Connect_DB.getInstance();
        connectDB.connet();
        Nuoc_Dao nuoc_Dao = new Nuoc_Dao(connectDB.getConnection());
       // qlnuocFr qln = new qlnuocFr(detail);
        qlnuocFr qln = new qlnuocFr(nuoc_Dao);
        this.setVisible(false);
        qln.setVisible(true);
    }

    private void btnQLNVActionPerformed(ActionEvent evt) throws SQLException {//GEN-FIRST:event_btnQLNVActionPerformed
        Connect_DB connectDB = Connect_DB.getInstance();
        connectDB.connet();
        NhanVienDAO nhanVienDAO = new NhanVienDAO(connectDB.getConnection());
        //qlnvFr qlnv = new qlnvFr(detail);
        qlnvFr qlnv = new qlnvFr(nhanVienDAO);
        this.setVisible(false);
        qlnv.setVisible(true);
    }

    private void btnBanhangActionPerformed(ActionEvent evt) {
    	banhangFr banhang = new banhangFr(detail);
        this.setVisible(false);
    
        banhang.setVisible(true);
    }

    private void btnDatbanActionPerformed(ActionEvent evt) throws SQLException {
    	Connect_DB connectDB = Connect_DB.getInstance();
        connectDB.connet();
        DatBanDAO datBanDAO = new DatBanDAO(connectDB.getConnection());
        
        //DatbanFr datban = new DatbanFr(detail);
        DatbanFr datban = new DatbanFr(datBanDAO);
        this.setVisible(false);
        datban.setVisible(true);
    }

    private void btnThongkeActionPerformed(ActionEvent evt) {
        ThongkeFr thongke = new ThongkeFr(detail);
        this.setVisible(false);
        thongke.setVisible(true);
    }

    private void btnAboutActionPerformed(ActionEvent evt) {
        AboutFr thongtin = new AboutFr(detail);
        this.setVisible(false);
        thongtin.setVisible(true);
    }

    private void btnLogoutActionPerformed(ActionEvent evt) {
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất khỏi tài khoản hay không?", "Thông Báo",2);
        if(Click ==JOptionPane.YES_OPTION){
            LoginFr dangnhap = new LoginFr();
            this.setVisible(false);
            dangnhap.setVisible(true);
        }
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainFr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainFr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainFr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFr.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Detail detail= new Detail();
                new MainFr(detail).setVisible(true);
            }
        });
    }

   
}
