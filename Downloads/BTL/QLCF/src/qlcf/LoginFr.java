
package qlcf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import connectBD.*;

public class LoginFr extends JFrame implements ActionListener {
    
    private static final long serialVersionUID = 1L;
	private ResultSet rs=null;
    private JTextField tfTaiKhoan;
    private JPasswordField pass;
    private JButton btnLogin;
    private JButton btnCancel;
    private JLabel lbTrangthai;

    private JPanel jPanel3;
    private JLabel lbPass;
    private JLabel lbUser;
   
    public LoginFr() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(this);
        
    }
    
    private boolean checkNull(){
        if(tfTaiKhoan.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập tài khoản đăng nhập!");
            return false;
        }
        else
        if(pass.getText().equals("")){
            lbTrangthai.setText("Bạn chưa nhập mật khẩu!");
            return false;
        }
        return true;
    }
    
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel(){
            private static final long serialVersionUID = 1L;
			ImageIcon icon = new ImageIcon("src/Photos/coffee.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        lbUser = new JLabel();
        lbPass = new JLabel();
        tfTaiKhoan = new JTextField();
        pass = new JPasswordField();
        btnLogin = new JButton();
        btnCancel = new JButton();
        lbTrangthai = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        addHierarchyBoundsListener(new HierarchyBoundsListener() {
            public void ancestorMoved(HierarchyEvent evt) {
                formAncestorMoved(evt);
            }
            public void ancestorResized(HierarchyEvent evt) {
            }
        });

        lbUser.setFont(new Font("Tahoma", 0, 14)); 
        lbUser.setText("Tài khoản:");

        lbPass.setFont(new Font("Tahoma", 0, 14)); 
        lbPass.setText("Mật khẩu:");

        tfTaiKhoan.setFont(new Font("Tahoma", 0, 14)); 

        pass.setFont(new Font("Tahoma", 0, 14)); 

        btnLogin.setFont(new Font("Tahoma", 0, 14)); 
        btnLogin.setIcon(new ImageIcon(getClass().getResource("/Photos/login.png"))); 
        btnLogin.setText("Đăng nhập");
        btnLogin.addActionListener(this);

        btnCancel.setFont(new Font("Tahoma", 0, 14)); 
        btnCancel.setIcon(new ImageIcon(getClass().getResource("/Photos/x-button.png"))); 
        btnCancel.setText("Thoát");
        btnCancel.addActionListener(this);

        lbTrangthai.setFont(new Font("Times New Roman", 0, 24));
        lbTrangthai.setForeground(new Color(255, 255, 0));
        lbTrangthai.setHorizontalAlignment(SwingConstants.CENTER);
        lbTrangthai.setText("Trạng thái");

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(256, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(lbUser)
                            .addComponent(lbPass))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfTaiKhoan, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addComponent(pass))
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnLogin)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)
                        .addGap(43, 43, 43))))
            .addComponent(lbTrangthai, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUser)
                    .addComponent(tfTaiKhoan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPass)
                    .addComponent(pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 32,GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(lbTrangthai, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3,GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        try {
			Connect_DB.getInstance().connet();
			System.out.println("Connect Login Thành Công !!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
    }

    private void formAncestorMoved(HierarchyEvent evt) {
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(btnCancel)){
            int click=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình hay không?", "Thông báo", 2);
            if(click==JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
        else if(o.equals(btnLogin)){
            if(checkNull())
                try {
                    String sql="SELECT * FROM QLNV";
                    rs = Connect_DB.getInstance().executeQuery(sql);
                    
                    while(rs.next()){
                        if(rs.getString("taiKhoan").toString().trim().equals(tfTaiKhoan.getText())&& rs.getString("matKhau").toString().trim().equals(pass.getText())){
                            
                            Detail detail=new Detail(rs.getString("taiKhoan").trim(),rs.getString("tenNV").trim());
                            
                            MainFr home = new MainFr(detail);
                            this.setVisible(false);
                            home.setVisible(true);
                            return;
                        }
                        else lbTrangthai.setText("Bạn đã nhập sai tài khoản hoặc mật khẩu!");
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
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
            Logger.getLogger(LoginFr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LoginFr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
           Logger.getLogger(LoginFr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LoginFr.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFr().setVisible(true);
            }
        });
    }
}

