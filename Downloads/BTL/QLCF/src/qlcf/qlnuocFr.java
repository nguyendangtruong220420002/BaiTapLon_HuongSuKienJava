
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
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import connectBD.Connect_DB;
import dao.Nuoc_Dao;
import entiy.Nuoc;

public class qlnuocFr extends JFrame {

	private static final long serialVersionUID = 1L;
	private Detail detail;
	private ResultSet rs = null;
	private boolean add = false, change = false;
	private static Nuoc_Dao nuoc_Dao;
	private List<Nuoc> nuocList;
	private DefaultTableModel tableModel;

	private JButton btnAdd;
	private JButton btnBack;
	private JButton btnCancel;
	private JButton btnDel;
	private JButton btnEdit;
	private JButton btnFind;
	private JButton btnSave;
	private JComboBox<String> cbLoaiNuoc;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JScrollPane jScrollPane1;
	private JLabel lbGia;
	private JLabel lbLoai;
	private JLabel lbMatu;
	private JLabel lbQLTU;
	private JLabel lbTen;
	private JLabel lbThongtin;
	private JLabel lbTrangthai;
	private static JTable tableDrink;
	private JTextField tfDonVi;
	private JTextField tfFind;
	private JTextField tfGia;
	private JTextField tfMatu;
	private JTextField tfSoLuong;
	private JTextField tfTen;

	public qlnuocFr(Nuoc_Dao nuoc_Dao) {
		initComponents();
		setResizable(false);
		setLocationRelativeTo(this);
		this.detail = new Detail();

		qlnuocFr.nuoc_Dao = nuoc_Dao;
		Disabled();
		loadData();
		loadLoaiNuoc();
	}

	private void checkKyTu(String arry) {
		char[] character = arry.toCharArray();
		for (int i = 0; i < character.length; i++) {
			if (String.valueOf(character[i]).matches("\\D+")) {
				btnSave.setEnabled(false);
				lbTrangthai.setText("Số lượng không thể chứa kí tự");
				break;
			} else
				btnSave.setEnabled(true);
		}
	}

	private String cutChar(String arry) {
		return arry.replaceAll("\\D+", "");
	}

	private void loadLoaiNuoc() {
		cbLoaiNuoc.removeAllItems();
		cbLoaiNuoc.addItem("Cafe");
		cbLoaiNuoc.addItem("Sinh Tố");
		cbLoaiNuoc.addItem("Nước Giải Khát");
	}

	private void loadData() {
		try {
			nuocList = nuoc_Dao.layDanhSachNuoc();
			displayDataInTable();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void displayDataInTable() {
		String[] arry = { "Mã Thức Uống", "Loại Nước", "Tên Nước", "Đơn Vị", "Số Lượng", "Giá Bán" };
		tableModel = new DefaultTableModel(arry, 0);
		tableDrink.setModel(tableModel);
		tableModel.setRowCount(0);
		for (Nuoc nuoc : nuocList) {
			Object[] rowData = { nuoc.getMaNuoc(), nuoc.getLoaiNuoc(), nuoc.getTenNuoc(), nuoc.getDonVi(),
					nuoc.getSoLuong(), nuoc.getGiaBan() };
			tableModel.addRow(rowData);
		}

	}

	private double convertedToNumbers(String s) {
		String number = "";
		String[] array = s.replace(",", " ").split("\\s");
		for (String i : array) {
			number = number.concat(i);
		}
		return Double.parseDouble(number);
	}

	private void Enabled() {
		tfMatu.setEnabled(true);
		cbLoaiNuoc.setEnabled(true);
		tfTen.setEnabled(true);
		tfDonVi.setEnabled(true);
		tfSoLuong.setEnabled(true);
		tfGia.setEnabled(true);
	}

	private void Disabled() {
		tfMatu.setEnabled(false);
		cbLoaiNuoc.setEnabled(false);
		tfTen.setEnabled(false);
		tfDonVi.setEnabled(false);
		tfSoLuong.setEnabled(false);
		tfGia.setEnabled(false);

	}

	private void reset() {
		add = false;
		change = false;
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

	private boolean checkNulledit() {
		if (tfMatu.getText().equals("")) {
			lbTrangthai.setText("Bạn chưa nhập mã thức uống!");
			return false;
		} else if (cbLoaiNuoc.getSelectedItem().equals("")) {
			lbTrangthai.setText("Bạn chưa chọn loại thức uống!");
			return false;
		} else if (tfTen.getText().equals("")) {
			lbTrangthai.setText("Bạn chưa nhập tên thức uống");
			return false;
		} else if (tfDonVi.getText().equals("")) {
			lbTrangthai.setText("Bạn chưa nhập đơn vị tính!");
			return false;
		} else if (tfSoLuong.getText().equals("")) {
			lbTrangthai.setText("Bạn chưa nhập số lượng nước!");
			return false;
		} else if (tfGia.getText().equals("")) {
			lbTrangthai.setText("Bạn chưa nhập giá!");
			return false;
		}
		return true;
	}

	private boolean checkNull() {
		// Kiểm tra mã thức uống không được để trống
		if (tfMatu.getText().isEmpty()) {
			lbTrangthai.setText("Bạn chưa nhập mã thức uống!");
			return false;
		}

		// Kiểm tra xem đã chọn loại nước chưa
		if (cbLoaiNuoc.getSelectedItem().toString().isEmpty()) {
			lbTrangthai.setText("Bạn chưa chọn loại thức uống!");
			return false;
		}

		// Kiểm tra tên thức uống không được để trống
		if (tfTen.getText().isEmpty()) {
			lbTrangthai.setText("Bạn chưa nhập tên thức uống");
			return false;
		}

		// Kiểm tra đơn vị tính không được để trống
		if (tfDonVi.getText().isEmpty()) {
			lbTrangthai.setText("Bạn chưa nhập đơn vị tính!");
			return false;
		}

		// Kiểm tra số lượng nước không được để trống và phải là số nguyên dương
		String soLuongStr = tfSoLuong.getText();
		if (soLuongStr.isEmpty()) {
			lbTrangthai.setText("Bạn chưa nhập số lượng nước!");
			return false;
		} else {
			try {
				int soLuong = Integer.parseInt(soLuongStr);
				if (soLuong <= 0) {
					lbTrangthai.setText("Số lượng nước phải là số nguyên dương!");
					return false;
				}
			} catch (NumberFormatException e) {
				lbTrangthai.setText("Số lượng nước không hợp lệ!");
				return false;
			}
		}

		// Kiểm tra giá không được để trống và phải là số
		String giaStr = tfGia.getText();
		if (giaStr.isEmpty()) {
			lbTrangthai.setText("Bạn chưa nhập giá!");
			return false;
		} else {
			try {
				double gia = Double.parseDouble(giaStr);
				if (gia <= 0) {
					lbTrangthai.setText("Giá phải là số dương!");
					return false;
				}
			} catch (NumberFormatException e) {
				lbTrangthai.setText("Giá không hợp lệ!");
				return false;
			}
		}

		// Kiểm tra xem mã thức uống đã tồn tại trong cơ sở dữ liệu hay chưa
		String maNuoc = tfMatu.getText();
		if (nuoc_Dao.kiemTraMaNuocTonTai(maNuoc)) {
			lbTrangthai.setText("Mã thức uống đã tồn tại!");
			return false;
		}

		return true;
	}

	private void addDrink() {
		if (checkNull()) {
			Nuoc newNuoc = createNuocFromInput();
			nuoc_Dao.themNuoc(newNuoc);
			reset(); // Đặt lại giao diện sau khi thêm thành công
			loadData(); // Tải lại dữ liệu từ cơ sở dữ liệu để hiển thị
			Disabled(); // Vô hiệu hóa các trường nhập liệu
			lbTrangthai.setText("Thêm nước thành công!");
		}
	}

	private Nuoc createNuocFromInput() {
		Nuoc newNuoc = new Nuoc();
		newNuoc.setMaNuoc(tfMatu.getText());
		newNuoc.setLoaiNuoc(cbLoaiNuoc.getSelectedItem().toString());
		newNuoc.setTenNuoc(tfTen.getText());
		newNuoc.setDonVi(tfDonVi.getText());
		try {
			int soLuong = Integer.parseInt(tfSoLuong.getText());
			newNuoc.setSoLuong(soLuong);
		} catch (NumberFormatException e) {
			lbTrangthai.setText("Số lượng không hợp lệ!");
			return null;
		}
		try {
			double giaBan = Double.parseDouble(tfGia.getText());
			newNuoc.setGiaBan(giaBan);
		} catch (NumberFormatException e) {
			lbTrangthai.setText("Giá bán không hợp lệ!");
			return null;
		}
		return newNuoc;
	}

	private void editDrink() {
		if (checkNulledit()) {
			int selectedIndex = tableDrink.getSelectedRow();
			if (selectedIndex != -1) { // Kiểm tra xem có dòng nào được chọn không
				Nuoc editedNuoc = new Nuoc();
				editedNuoc.setMaNuoc(tfMatu.getText());
				editedNuoc.setLoaiNuoc(cbLoaiNuoc.getSelectedItem().toString());
				editedNuoc.setTenNuoc(tfTen.getText());

				// Kiểm tra và xử lý giá trị của tfGia
				String giaStr = tfGia.getText();
				try {
					double giaDouble = Double.parseDouble(giaStr);
					editedNuoc.setGiaBan(giaDouble);
				} catch (NumberFormatException e) {
					// Xử lý trường hợp không thể chuyển đổi thành số
					lbTrangthai.setText("Giá tiền không hợp lệ");
					return;
				}
				editedNuoc.setDonVi(tfDonVi.getText());

				// Kiểm tra và xử lý giá trị của tfSoLuong
				String soLuongStr = tfSoLuong.getText();
				try {
					int soLuongInt = Integer.parseInt(soLuongStr);
					editedNuoc.setSoLuong(soLuongInt);
				} catch (NumberFormatException e) {
					// Xử lý trường hợp không thể chuyển đổi thành số
					lbTrangthai.setText("Số lượng không hợp lệ");
					return;
				}
				nuoc_Dao.capNhatNuoc(editedNuoc); // Cập nhật thông tin vào cơ sở dữ liệu
				reset();
				loadData();
				Disabled();
				lbTrangthai.setText("Thay đổi thông tin thức uống thành công");
			} else {
				lbTrangthai.setText("Vui lòng chọn một thức uống để sửa đổi");
			}
		}
	}

	private boolean check() {
		try {
			while (rs.next()) {
				if (rs.getString("maNuoc").toString().trim().equals(tfMatu.getText())) {
					lbTrangthai.setText("Mã nước bạn nhập đã tồn tại");
					return false;
				} else if (rs.getString("tenNuoc").toString().trim().equals(tfTen.getText())) {
					lbTrangthai.setText("Thức uống bạn nhập đã tồn tại");
					return false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	private void initComponents() {

		lbQLTU = new JLabel();
		jPanel1 = new JPanel();
		lbMatu = new JLabel();
		lbLoai = new JLabel();
		lbTen = new JLabel();
		lbGia = new JLabel();
		tfMatu = new JTextField();
		tfTen = new JTextField();
		tfGia = new JTextField();
		cbLoaiNuoc = new JComboBox<>();
		lbThongtin = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		tfDonVi = new JTextField();
		tfSoLuong = new JTextField();
		jScrollPane1 = new JScrollPane();
		tableDrink = new JTable();
		tfFind = new JTextField();
		btnFind = new JButton();
		btnBack = new JButton();
		jPanel2 = new JPanel();
		btnAdd = new JButton();
		btnEdit = new JButton();
		btnDel = new JButton();
		btnSave = new JButton();
		btnCancel = new JButton();
		lbTrangthai = new JLabel();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Quản lý thức uống");

		lbQLTU.setFont(new Font("Tahoma", 0, 48));
		lbQLTU.setText("Quản lý thức uống");

		lbMatu.setFont(new Font("Tahoma", 0, 14));
		lbMatu.setText("Mã thức uống:");

		lbLoai.setFont(new Font("Tahoma", 0, 14));
		lbLoai.setText("Loại thức uống:");

		lbTen.setFont(new Font("Tahoma", 0, 14));
		lbTen.setText("Tên thức uống:");

		lbGia.setFont(new Font("Tahoma", 0, 14));
		lbGia.setText("Giá tiền:");

		tfMatu.setFont(new Font("Tahoma", 0, 14));

		tfTen.setFont(new Font("Tahoma", 0, 14));

		tfGia.setFont(new Font("Tahoma", 0, 14));
		tfGia.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				tfGiaKeyReleased();
			}
		});

		cbLoaiNuoc.setFont(new Font("Tahoma", 0, 14));

		lbThongtin.setFont(new Font("Tahoma", 0, 24));
		lbThongtin.setText("Thông tin Thức uống");

		jLabel2.setFont(new Font("Tahoma", 0, 14));
		jLabel2.setText("Đơn vị tính:");

		jLabel3.setFont(new Font("Tahoma", 0, 14));
		jLabel3.setText("Số lượng:");

		tfDonVi.setFont(new Font("Tahoma", 0, 14));

		tfSoLuong.setFont(new Font("Tahoma", 0, 14));
		tfSoLuong.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				tfSoLuongKeyReleased();
			}
		});

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(96, 96, 96).addComponent(lbThongtin))
						.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addComponent(lbLoai).addComponent(lbMatu).addComponent(lbTen)
										.addComponent(jLabel2).addComponent(jLabel3).addComponent(lbGia))
								.addGap(18, 18, 18)
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(cbLoaiNuoc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(tfMatu).addComponent(tfDonVi).addComponent(tfSoLuong)))
						.addGroup(GroupLayout.Alignment.TRAILING,
								jPanel1Layout.createSequentialGroup().addContainerGap()
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(tfTen, GroupLayout.Alignment.TRAILING,
														GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
												.addComponent(tfGia, GroupLayout.Alignment.TRAILING,
														GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addComponent(lbThongtin)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lbMatu).addComponent(tfMatu, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lbLoai).addComponent(cbLoaiNuoc, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lbTen).addComponent(tfTen, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel2).addComponent(tfDonVi, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel3).addComponent(tfSoLuong, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lbGia).addComponent(tfGia, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		tableDrink.setFont(new Font("Tahoma", 0, 12));
		tableDrink.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } },
				new String[] { "Mã thức uống", "Loại thức uống", "Tên thức uống", "Giá bán" }) {
			private static final long serialVersionUID = 1L;
			Class[] types = new Class[] { Integer.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});
		tableDrink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tableDrinkMouseClicked();
			}
		});
		jScrollPane1.setViewportView(tableDrink);
		if (tableDrink.getColumnModel().getColumnCount() > 0) {
			tableDrink.getColumnModel().getColumn(0).setPreferredWidth(30);
			tableDrink.getColumnModel().getColumn(1).setPreferredWidth(25);
			tableDrink.getColumnModel().getColumn(3).setPreferredWidth(20);
		}

		tfFind.setFont(new Font("Tahoma", 0, 14));

		btnFind.setFont(new Font("Tahoma", 0, 14));
		btnFind.setIcon(new ImageIcon(getClass().getResource("/Photos/search (1).png")));
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnFindActionPerformed();
			}
		});

		btnBack.setFont(new Font("Tahoma", 0, 14));
		btnBack.setIcon(new ImageIcon(getClass().getResource("/Photos/smart-home.png")));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnBackActionPerformed();
			}
		});

		btnAdd.setFont(new Font("Tahoma", 0, 14));
		btnAdd.setIcon(new ImageIcon(getClass().getResource("/Photos/plus.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnAddActionPerformed();
			}
		});

		btnEdit.setFont(new Font("Tahoma", 0, 14));
		btnEdit.setIcon(new ImageIcon(getClass().getResource("/Photos/edit.png")));
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnEditActionPerformed();
			}
		});

		btnDel.setFont(new Font("Tahoma", 0, 14));
		btnDel.setIcon(new ImageIcon(getClass().getResource("/Photos/delete.png")));
		btnDel.setEnabled(false);
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnDelActionPerformed();
			}
		});

		btnSave.setFont(new Font("Tahoma", 0, 14));
		btnSave.setIcon(new ImageIcon(getClass().getResource("/Photos/floppy-disk.png")));
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
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(btnSave)
						.addGap(27, 27, 27)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(btnDel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 70,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnDel, GroupLayout.PREFERRED_SIZE, 70,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 70,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 70,
												GroupLayout.PREFERRED_SIZE))
								.addGap(0, 0, Short.MAX_VALUE))
						.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));

		lbTrangthai.setFont(new Font("Tahoma", 0, 14));
		lbTrangthai.setHorizontalAlignment(SwingConstants.CENTER);
		lbTrangthai.setText("Trạng thái");

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(btnBack)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(lbQLTU).addGap(246, 246, 246))
				.addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(tfFind, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(btnFind, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(lbTrangthai, GroupLayout.PREFERRED_SIZE, 376,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup()
														.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(29, 29, 29)))
								.addGap(13, 13, 13))
						.addGroup(layout.createSequentialGroup()
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 620, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(43, 43, 43)))));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(lbQLTU))
								.addComponent(btnBack))
						.addGap(26, 26, 26)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												lbTrangthai, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
										.addGap(29, 29, 29).addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 176,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
														.addComponent(tfFind, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(36, 36, 36))
												.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
														.addComponent(btnFind).addGap(27, 27, 27)))))));

		pack();
		try {
			Connect_DB.getInstance().connet();
			System.out.println("Connect QuanLiNuoc Thành Công !!!");
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

	private void btnDelActionPerformed() {
		int click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thức uống này hay không?", "Thông báo", 2);
		if (click == JOptionPane.YES_OPTION) {
			int selectedIndex = tableDrink.getSelectedRow();
			if (selectedIndex != -1) {
				String maNuoc = (String) tableDrink.getValueAt(selectedIndex, 0);
				nuoc_Dao.xoaNuoc(maNuoc); // Gọi phương thức xóa từ DAO
				loadData();
				reset();
				Disabled();
				lbTrangthai.setText("Xóa thành công thức uống!");
			}
		} else {
			reset();
		}
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

	private void btnCancelActionPerformed() {
		Disabled();
		reset();
		loadData();
	}

	private void btnBackActionPerformed() {
		MainFr home = new MainFr(detail);
		this.setVisible(false);
		home.setVisible(true);
	}

	private void tableDrinkMouseClicked() {
		cbLoaiNuoc.removeAllItems();
		int click = tableDrink.getSelectedRow();
		TableModel model = tableDrink.getModel();

		tfMatu.setText(model.getValueAt(click, 0).toString());
		cbLoaiNuoc.addItem(model.getValueAt(click, 1).toString());
		tfTen.setText(model.getValueAt(click, 2).toString());
		tfDonVi.setText(model.getValueAt(click, 3).toString());
		tfSoLuong.setText(model.getValueAt(click, 4).toString());

		String[] s1 = model.getValueAt(click, 5).toString().split("\\s");
		tfGia.setText(s1[0]);

		tfGia.setText(model.getValueAt(click, 5).toString());

		this.btnEdit.setEnabled(true);
		this.btnDel.setEnabled(true);
	}

	private void btnSaveActionPerformed() {
		if (add == true) {
			if (check()) {
				addDrink();
			}
		} else {
			if (change == true)
				editDrink();
		}
	}

	private void btnFindActionPerformed() {
		String searchTerm = tfFind.getText();
		if (!searchTerm.isEmpty()) {
			List<Nuoc> searchResults = nuoc_Dao.timNuoc(searchTerm); // Gọi phương thức tìm kiếm từ DAO
			displaySearchResults(searchResults); // Hiển thị kết quả tìm kiếm
			lbTrangthai.setText("Đã tìm thấy " + searchResults.size() + " kết quả.");
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm.", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// Phương thức để hiển thị kết quả tìm kiếm trên bảng
	private void displaySearchResults(List<Nuoc> searchResults) {
		DefaultTableModel tableModel = (DefaultTableModel) tableDrink.getModel();
		tableModel.setRowCount(0); // Xóa tất cả các dòng hiện tại trong bảng
		for (Nuoc nuoc : searchResults) {
			Object[] rowData = { nuoc.getMaNuoc(), nuoc.getLoaiNuoc(), nuoc.getTenNuoc(), nuoc.getDonVi(),
					nuoc.getSoLuong(), nuoc.getGiaBan() };
			tableModel.addRow(rowData); // Thêm một dòng mới cho mỗi kết quả tìm kiếm
		}
	}

	private void tfGiaKeyReleased() {
		DecimalFormat formatter = new DecimalFormat("###,###,###");

		if (tfGia.getText().equals("")) {
			return;
		} else if (tfGia.getText().matches("\\D+")) {
			tfGia.setText(cutChar(tfGia.getText()));
		} else {
			tfGia.setText(formatter.format(convertedToNumbers(cutChar(tfGia.getText()))));
		}
	}

	private void tfSoLuongKeyReleased() {
		checkKyTu(this.tfSoLuong.getText());
		tfSoLuong.setText(cutChar(tfSoLuong.getText()));
	}

	public static void main(String args[]) {
		// do hoa
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(qlnuocFr.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(qlnuocFr.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(qlnuocFr.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(qlnuocFr.class.getName()).log(Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Connect_DB connectDB = Connect_DB.getInstance();
				try {
					connectDB.connet();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Kết nối với cơ sở dữ liệu
				Nuoc_Dao nuoc_Dao = new Nuoc_Dao(Connect_DB.getConnection());
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						qlnuocFr qlnuocFr = new qlnuocFr(nuoc_Dao);
						qlnuocFr.setVisible(true);

					}
				});
			}
		});
	}

}
