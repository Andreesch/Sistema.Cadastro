
/**
CADASTRO DE CLIENTES
ACADÊMICO: THARLYSON JR. S. RIBEIRO
CURSO: CIÊNCIA DA COMPUTAÇÃO
**/

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.Rectangle;
import java.awt.SplashScreen;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JSlider;

@SuppressWarnings({ "serial", "unused" })
public class Main extends JFrame implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	}

	private JTextField CampoId;
	private static JTextField CampoNome;
	private static JTextField CampoEndereco;
	private static JLabel LabelCpf;
	private static JFormattedTextField CampoCpf_1 = new JFormattedTextField();
	private static JTextField CampoEmail;
	private static JFormattedTextField CampoTelefone_1;
	static JFormattedTextField CampoCelular = new JFormattedTextField((setMascara("(##)###-####")));

	private static MaskFormatter setMascara(String mascara) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(mascara);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}
	// MATRIZ CLIENTES

	static int id = 0;
	static int clientes = 100;
	static int dados = 5;
	static int telefone = 2;

	static String[][] DB = new String[clientes][dados];
	static String[][] DBT = new String[clientes][telefone];

	private static JTable TabelaClientes;

	public void ConsultaClientes() {

		setVisible(true);
		setSize(820, 528);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("CONSULTA DE CLIENTES");
		getContentPane().setLayout(null);

		JLabel ImagemCadastro = new JLabel(new ImageIcon(getClass().getResource("cadastro.png")));
		ImagemCadastro.setBounds(98, 23, 65, 57);
		getContentPane().add(ImagemCadastro);

		JLabel lblTelefone = new JLabel("TELEFONE CELULAR ");
		lblTelefone.setFont(new Font("Roboto Condensed", Font.PLAIN, 13));
		lblTelefone.setBounds(500, 402, 277, 16);
		getContentPane().add(lblTelefone);

		final JButton BotaoExcluir = new JButton("EXCLUIR");
		BotaoExcluir.setEnabled(true);
		BotaoExcluir.setBounds(33, 420, 121, 40);
		getContentPane().add(BotaoExcluir);
		BotaoExcluir.setIcon(
				new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/collapsed-rtl.gif")));
		BotaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == BotaoExcluir) {

					if (TabelaClientes.getSelectedRow() != -1) {

						final DefaultTableModel valores = (DefaultTableModel) TabelaClientes.getModel();

						((DefaultTableModel) TabelaClientes.getModel()).removeRow(TabelaClientes.getSelectedRow());

						id--;

						JOptionPane.showMessageDialog(null, "O CLIENTE FOI EXCLUIDO COM SUCESSO");

						JPanel TrocarTela = null;
						TrocarTela = new JPanel();
						getContentPane().removeAll();
						getContentPane().add(TrocarTela);
						repaint();
						revalidate();
						ConsultaClientes();

					} else {

						JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada.");

					}

				}
			}
		}

		);

		final JButton BotaoVoltar = new JButton("VOLTAR");
		BotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				if (e.getSource() == BotaoVoltar) {
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					TelaInicial();
				}
			}
		});
		BotaoVoltar.setBounds(299, 419, 111, 40);
		getContentPane().add(BotaoVoltar);

		final JButton BotaoEditar = new JButton("ATUALIZAR");
		BotaoEditar.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		BotaoEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TabelaClientes.getSelectedRow() != -1) {
					JPanel TrocarTela = null;
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					AtualizarClientes();

				} else {

					JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada.");

				}
			}

		});
		BotaoEditar.setBounds(166, 420, 121, 40);
		getContentPane().add(BotaoEditar);

		JLabel lblConsultarClientes = new JLabel("CONSULTAR CLIENTES");
		lblConsultarClientes.setFont(new Font("Roboto Condensed", Font.PLAIN, 21));
		lblConsultarClientes.setBounds(317, 50, 199, 16);
		getContentPane().add(lblConsultarClientes);

		JScrollPane ScrolTabelaClientes = new JScrollPane();
		ScrolTabelaClientes.setBounds(33, 115, 744, 288);
		getContentPane().add(ScrolTabelaClientes);

		TabelaClientes = new JTable();
		TabelaClientes.setShowGrid(false);
		TabelaClientes.setShowHorizontalLines(false);
		ScrolTabelaClientes.setViewportView(TabelaClientes);
		TabelaClientes.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "NOME", "CPF", "ENDERECO", "EMAIL", "TELEFONE FIXO", "TELEFONE CEL" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		JButton btnTelefone = new JButton("CADASTRAR | EDITAR\r\n");
		btnTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (TabelaClientes.getSelectedRow() != -1) {

					JPanel TrocarTela = null;
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					CadastrarTelefone();

				} else {

					JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada.");

				}
			}

		});

		btnTelefone.setBounds(500, 420, 167, 40);
		getContentPane().add(btnTelefone);

		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int selectColumn = TabelaClientes.getSelectedColumn();
				
				if (TabelaClientes.getSelectedRow() != -1) {
					
					
					int MatrizDeletarTelefone = ContarLinhas();

					DBT[MatrizDeletarTelefone - 1][0] = "";

					JOptionPane.showMessageDialog(null, "O TELEFONE FOI EXCLUIDO COM SUCESSO");

					
					JPanel TrocarTela = null;
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					repaint();
					revalidate();
					ConsultaClientes();

				} else {

					JOptionPane.showMessageDialog(null, "Nenhuma linha foi selecionada.");

				}

			}

			private String CampoCelular(MaskFormatter maskFormatter) {
				// TODO Auto-generated method stub
				return null;
			}

		}

		);

		btnExcluir.setBounds(679, 420, 98, 40);
		getContentPane().add(btnExcluir);

		TabelaClientes.getTableHeader().setReorderingAllowed(false);
		TabelaClientes.getColumnModel().getColumn(0).setPreferredWidth(28);
		TabelaClientes.getColumnModel().getColumn(1).setPreferredWidth(207);
		TabelaClientes.getColumnModel().getColumn(2).setPreferredWidth(107);
		TabelaClientes.getColumnModel().getColumn(3).setPreferredWidth(134);
		TabelaClientes.getColumnModel().getColumn(4).setPreferredWidth(123);
		TabelaClientes.getColumnModel().getColumn(5).setPreferredWidth(116);
		TabelaClientes.getColumnModel().getColumn(6).setPreferredWidth(116);

		TabelaClientes.getColumnModel().getColumn(0).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(1).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(2).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(3).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(4).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(5).setResizable(false);
		TabelaClientes.getColumnModel().getColumn(6).setResizable(false);

		for (int i = 0; i < id; i++) {
			final DefaultTableModel valores = (DefaultTableModel) TabelaClientes.getModel();
			valores.addRow(new Object[] { i + 1, DB[i][0], DB[i][1], DB[i][2], DB[i][3], DB[i][4], DBT[i][0] });
		}

	}

	public void CadastroCliente() {

		JButton Limpar1 = new JButton("LIMPAR");
		Limpar1.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		getContentPane().add(Limpar1);
		Limpar1.setBounds(180, 433, 130, 40);
		Limpar1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				CampoNome.setText("");
				CampoCpf_1.setText(null);
				CampoEndereco.setText("");
				CampoEmail.setText("");
				CampoTelefone_1.setText(null);

			}
		});

		JLabel LabelId = new JLabel("ID");
		LabelId.setVerticalAlignment(SwingConstants.BOTTOM);
		LabelId.setBounds(138, 180, 23, 17);
		getContentPane().add(LabelId);

		LabelCpf = new JLabel("CPF");
		LabelCpf.setBounds(138, 230, 29, 14);
		getContentPane().add(LabelCpf);

		JLabel LabelEndereco = new JLabel("ENDERE\u00C7O");
		LabelEndereco.setBounds(355, 227, 81, 14);
		getContentPane().add(LabelEndereco);

		final JFormattedTextField CampoTelefone_1 = new JFormattedTextField((setMascara("(##)####-####")));
		CampoTelefone_1.setToolTipText("");
		CampoTelefone_1.setBounds(540, 264, 114, 26);
		getContentPane().add(CampoTelefone_1);
		CampoTelefone_1.setColumns(10);

		CampoEmail = new JTextField();
		CampoEmail.setBounds(187, 264, 217, 26);
		getContentPane().add(CampoEmail);
		CampoEmail.setColumns(10);

		CampoId = new JTextField();
		CampoId.setEditable(false);
		CampoId.setMargin(new Insets(0, 4, 0, 0));
		CampoId.setBounds(new Rectangle(3, 0, 0, 0));
		CampoId.setBounds(171, 175, 29, 26);
		getContentPane().add(CampoId);
		CampoId.setColumns(10);
		CampoId.setText(String.valueOf(id + 1));

		JLabel LabelEmail = new JLabel("EMAIL");
		LabelEmail.setBounds(138, 270, 46, 14);
		getContentPane().add(LabelEmail);

		CampoEndereco = new JTextField();
		CampoEndereco.setBounds(429, 221, 224, 26);
		getContentPane().add(CampoEndereco);
		CampoEndereco.setColumns(10);

		JLabel LabelNome = new JLabel("NOME");
		LabelNome.setBounds(233, 183, 40, 14);
		getContentPane().add(LabelNome);

		CampoNome = new JTextField();
		CampoNome.setBounds(283, 177, 370, 26);
		getContentPane().add(CampoNome);
		CampoNome.setColumns(10);

		JLabel LabelTelefone = new JLabel("TELEFONE FIXO");
		LabelTelefone.setBounds(429, 270, 165, 14);
		getContentPane().add(LabelTelefone);

		final JFormattedTextField CampoCpf_1 = new JFormattedTextField((setMascara("###.###.###-##")));
		CampoCpf_1.setToolTipText("");
		CampoCpf_1.setBounds(177, 224, 159, 26);
		getContentPane().add(CampoCpf_1);
		CampoCpf_1.setColumns(10);

		JLabel ImagemCadastro = new JLabel(new ImageIcon(getClass().getResource("cadastro.png")));
		ImagemCadastro.setBounds(98, 23, 65, 57);
		getContentPane().add(ImagemCadastro);

		JLabel LabelCadastroDeCliente = new JLabel("CADASTRO DE CLIENTE");
		LabelCadastroDeCliente.setFont(new Font("Roboto Condensed", Font.PLAIN, 21));
		LabelCadastroDeCliente.setBounds(303, 49, 208, 31);
		getContentPane().add(LabelCadastroDeCliente);

		final JButton BotaoSalvar = new JButton("SALVAR");
		BotaoSalvar.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		BotaoSalvar.setBounds(35, 433, 130, 40);
		getContentPane().add(BotaoSalvar);

		setTitle("CADASTRE UM CLIENTE");
		getContentPane().setLayout(null);

		final JButton BotaoVoltar2 = new JButton("VOLTAR");
		BotaoVoltar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				if (e.getSource() == BotaoVoltar2) {
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					TelaInicial();
				}
			}
		});
		BotaoVoltar2.setBounds(322, 433, 130, 40);
		getContentPane().add(BotaoVoltar2);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(35, 108, 744, 302);
		getContentPane().add(panel);

		setSize(820, 528);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

		BotaoSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == BotaoSalvar) {
					if (CampoNome != null && CampoNome.getText().equals("") == true) {
						JOptionPane.showMessageDialog(null, "Digite o nome");
					} else if (CampoCpf_1 != null && CampoCpf_1.getText().equals("   .   .   -  ") == true) {
						JOptionPane.showMessageDialog(null, "Digite o CPF");
					} else if (CampoEndereco != null && CampoEndereco.getText().equals("") == true) {
						JOptionPane.showMessageDialog(null, "Digite o endereço");
					} else if (CampoEmail != null && CampoEmail.getText().equals("") == true) {
						JOptionPane.showMessageDialog(null, "Digite o email");
					} else if (CampoTelefone_1 != null && CampoTelefone_1.getText().equals(null) == true) {
						JOptionPane.showMessageDialog(null, "Digite o telefone");
					} else {

						DB[id][0] = CampoNome.getText();
						DB[id][1] = CampoCpf_1.getText();
						DB[id][2] = CampoEndereco.getText();
						DB[id][3] = CampoEmail.getText();
						DB[id][4] = CampoTelefone_1.getText();

						id++;

						JOptionPane.showMessageDialog(null, "CLIENTE CADASTRADO");
						JPanel TrocarTela = null;
						TrocarTela = new JPanel();
						getContentPane().removeAll();
						getContentPane().add(TrocarTela);
						repaint();
						revalidate();
						CadastroCliente();

					}
				}
			}

		});

	}

	public void CadastrarTelefone() {

		JLabel LabelId = new JLabel("ID");
		LabelId.setVerticalAlignment(SwingConstants.BOTTOM);
		LabelId.setBounds(138, 180, 23, 17);
		getContentPane().add(LabelId);

		LabelCpf = new JLabel("CPF");
		LabelCpf.setBounds(138, 227, 29, 14);
		getContentPane().add(LabelCpf);

		JLabel LabelEndereco = new JLabel("ENDERE\u00C7O");
		LabelEndereco.setBounds(355, 227, 81, 14);
		getContentPane().add(LabelEndereco);

		final JFormattedTextField CampoTelefone_1 = new JFormattedTextField((setMascara(" (##)####-####")));
		CampoTelefone_1.setEnabled(false);
		CampoTelefone_1.setEditable(false);
		CampoTelefone_1.setToolTipText("");
		CampoTelefone_1.setBounds(540, 264, 114, 26);
		getContentPane().add(CampoTelefone_1);
		CampoTelefone_1.setColumns(10);
		CampoTelefone_1.setText(DB[Integer.parseInt(CampoId.getText()) - 1][4]);

		int LinhaTabela = TabelaClientes.getSelectedRow();
		final int EditarMatriz = (int) TabelaClientes.getModel().getValueAt(LinhaTabela, 0);

		CampoEmail = new JTextField();
		CampoEmail.setEnabled(false);
		CampoEmail.setEditable(false);
		CampoEmail.setBounds(187, 267, 217, 26);
		getContentPane().add(CampoEmail);
		CampoEmail.setColumns(10);
		CampoEmail.setText(DB[Integer.parseInt(CampoId.getText()) - 1][3]);

		CampoId = new JTextField();
		CampoId.setEnabled(false);
		CampoId.setEditable(false);
		CampoId.setMargin(new Insets(0, 7, 0, 0));
		CampoId.setBounds(new Rectangle(3, 0, 0, 0));
		CampoId.setBounds(171, 180, 29, 26);
		getContentPane().add(CampoId);
		CampoId.setColumns(10);
		CampoId.setText(String.valueOf(LinhaTabela + 1));

		JLabel LabelEmail = new JLabel("EMAIL");
		LabelEmail.setBounds(138, 270, 46, 14);
		getContentPane().add(LabelEmail);

		CampoEndereco = new JTextField();
		CampoEndereco.setEnabled(false);
		CampoEndereco.setEditable(false);
		CampoEndereco.setBounds(429, 224, 224, 26);
		getContentPane().add(CampoEndereco);
		CampoEndereco.setColumns(10);
		CampoEndereco.setText(DB[Integer.parseInt(CampoId.getText()) - 1][3]);

		JLabel LabelNome = new JLabel("NOME");
		LabelNome.setBounds(233, 183, 40, 14);
		getContentPane().add(LabelNome);

		CampoNome = new JTextField();
		CampoNome.setEnabled(false);
		CampoNome.setEditable(false);
		CampoNome.setBounds(283, 180, 370, 26);
		getContentPane().add(CampoNome);
		CampoNome.setColumns(10);
		CampoNome.setText(DB[Integer.parseInt(CampoId.getText()) - 1][0]);

		JLabel LabelTelefone = new JLabel("TELEFONE FIXO");
		LabelTelefone.setBounds(429, 270, 165, 14);
		getContentPane().add(LabelTelefone);

		final JFormattedTextField CampoCpf_1 = new JFormattedTextField((setMascara("###.###.###-##")));
		CampoCpf_1.setEnabled(false);
		CampoCpf_1.setEditable(false);
		CampoCpf_1.setToolTipText("");
		CampoCpf_1.setBounds(177, 224, 159, 26);
		getContentPane().add(CampoCpf_1);
		CampoCpf_1.setColumns(10);
		CampoCpf_1.setText(DB[Integer.parseInt(CampoId.getText()) - 1][1]);

		JLabel ImagemCadastro = new JLabel(new ImageIcon(getClass().getResource("cadastro.png")));
		ImagemCadastro.setBounds(98, 23, 65, 57);
		getContentPane().add(ImagemCadastro);

		JLabel LabelCadastroDeCliente = new JLabel("CADASTRAR | ATUALIZAR TELEFONE");
		LabelCadastroDeCliente.setFont(new Font("Roboto Condensed", Font.PLAIN, 21));
		LabelCadastroDeCliente.setBounds(250, 49, 314, 31);
		getContentPane().add(LabelCadastroDeCliente);

		final JButton BotaoSalvar = new JButton("SALVAR");
		BotaoSalvar.setIcon(
				new ImageIcon(Main.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		BotaoSalvar.setBounds(35, 433, 130, 33);
		getContentPane().add(BotaoSalvar);

		setTitle("CADASTRE UM CLIENTE");
		getContentPane().setLayout(null);

		final JButton BotaoVoltar2 = new JButton("VOLTAR");
		BotaoVoltar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				if (e.getSource() == BotaoVoltar2) {
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					ConsultaClientes();
				}
			}
		});
		BotaoVoltar2.setBounds(187, 433, 130, 33);
		getContentPane().add(BotaoVoltar2);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(35, 108, 744, 302);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblTelefoneCelular = new JLabel("TELEFONE CELULAR");
		lblTelefoneCelular.setBounds(106, 210, 127, 16);
		panel.add(lblTelefoneCelular);

		
		CampoCelular.setBounds(236, 205, 114, 26);
		panel.add(CampoCelular);
		CampoCelular.setColumns(10);

		setSize(820, 528);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

		BotaoSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == BotaoSalvar) {

					if (CampoCelular != null && CampoCelular.getText().equals(null) == true) {
						JOptionPane.showMessageDialog(null, "Digite o telefone");
					} else {

						int MatrizSalvarTelefone = ContarLinhas();

						DBT[MatrizSalvarTelefone - 1][0] = CampoCelular.getText();

						JOptionPane.showMessageDialog(null, "TELEFONE CADASTRADO COM SUCESSO.");
						JPanel TrocarTela = null;
						TrocarTela = new JPanel();
						getContentPane().removeAll();
						getContentPane().add(TrocarTela);
						revalidate();
						repaint();
						ConsultaClientes();

					}

				}
			}

		});

	}

	static int ContarLinhas() {

		int Linha = TabelaClientes.getSelectedRow();
		int MatrizSalvarTelefone = (int) TabelaClientes.getValueAt(Linha, 0);

		return MatrizSalvarTelefone;
	}

	public void AtualizarClientes() {
		JButton Limpar2 = new JButton("LIMPAR");
		getContentPane().add(Limpar2);
		Limpar2.setBounds(180, 433, 130, 33);
		Limpar2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				CampoNome.setText("");
				CampoCpf_1.setText("");
				CampoEndereco.setText("");
				CampoEmail.setText("");
				CampoTelefone_1.setText("");

			}
		});

		JLabel LabelId = new JLabel("ID");
		LabelId.setVerticalAlignment(SwingConstants.BOTTOM);
		LabelId.setBounds(138, 180, 23, 17);
		getContentPane().add(LabelId);

		LabelCpf = new JLabel("CPF");
		LabelCpf.setBounds(138, 227, 29, 14);
		getContentPane().add(LabelCpf);

		JLabel LabelEndereco = new JLabel("ENDERE\u00C7O");
		LabelEndereco.setBounds(355, 227, 81, 14);
		getContentPane().add(LabelEndereco);

		final JFormattedTextField CampoTelefone_1 = new JFormattedTextField((setMascara(" (##)####-####")));
		CampoTelefone_1.setToolTipText("");
		CampoTelefone_1.setBounds(540, 264, 114, 26);
		getContentPane().add(CampoTelefone_1);
		CampoTelefone_1.setColumns(10);
		CampoTelefone_1.setText(DB[Integer.parseInt(CampoId.getText()) - 1][4]);

		int LinhaTabela = TabelaClientes.getSelectedRow();
		final int EditarMatriz = (int) TabelaClientes.getModel().getValueAt(LinhaTabela, 0);

		CampoEmail = new JTextField();
		CampoEmail.setBounds(187, 267, 217, 26);
		getContentPane().add(CampoEmail);
		CampoEmail.setColumns(10);
		CampoEmail.setText(DB[Integer.parseInt(CampoId.getText()) - 1][3]);

		CampoId = new JTextField();
		CampoId.setEditable(false);
		CampoId.setMargin(new Insets(0, 7, 0, 0));
		CampoId.setBounds(new Rectangle(3, 0, 0, 0));
		CampoId.setBounds(171, 180, 29, 26);
		getContentPane().add(CampoId);
		CampoId.setColumns(10);
		CampoId.setText(String.valueOf(LinhaTabela + 1));

		JLabel LabelEmail = new JLabel("EMAIL");
		LabelEmail.setBounds(138, 270, 46, 14);
		getContentPane().add(LabelEmail);

		CampoEndereco = new JTextField();
		CampoEndereco.setBounds(429, 224, 224, 26);
		getContentPane().add(CampoEndereco);
		CampoEndereco.setColumns(10);
		CampoEndereco.setText(DB[Integer.parseInt(CampoId.getText()) - 1][3]);

		JLabel LabelNome = new JLabel("NOME");
		LabelNome.setBounds(233, 183, 40, 14);
		getContentPane().add(LabelNome);

		CampoNome = new JTextField();
		CampoNome.setBounds(283, 180, 370, 26);
		getContentPane().add(CampoNome);
		CampoNome.setColumns(10);
		CampoNome.setText(DB[Integer.parseInt(CampoId.getText()) - 1][0]);

		JLabel LabelTelefone = new JLabel("TELEFONE FIXO");
		LabelTelefone.setBounds(429, 270, 165, 14);
		getContentPane().add(LabelTelefone);

		final JFormattedTextField CampoCpf_1 = new JFormattedTextField((setMascara("###.###.###-##")));
		CampoCpf_1.setToolTipText("");
		CampoCpf_1.setBounds(177, 224, 159, 26);
		getContentPane().add(CampoCpf_1);
		CampoCpf_1.setColumns(10);
		CampoCpf_1.setText(DB[Integer.parseInt(CampoId.getText()) - 1][1]);

		JLabel ImagemCadastro = new JLabel(new ImageIcon(getClass().getResource("cadastro.png")));
		ImagemCadastro.setBounds(98, 23, 65, 57);
		getContentPane().add(ImagemCadastro);

		JLabel LabelCadastroDeCliente = new JLabel("EDITAR CLIENTES");
		LabelCadastroDeCliente.setFont(new Font("Roboto Condensed", Font.PLAIN, 21));
		LabelCadastroDeCliente.setBounds(333, 49, 148, 14);
		getContentPane().add(LabelCadastroDeCliente);

		final JButton BotaoSalvar = new JButton("SALVAR");
		BotaoSalvar.setIcon(
				new ImageIcon(Main.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		BotaoSalvar.setBounds(35, 433, 130, 33);
		getContentPane().add(BotaoSalvar);

		setTitle("CADASTRE UM CLIENTE");
		getContentPane().setLayout(null);

		final JButton BotaoVoltar2 = new JButton("VOLTAR");
		BotaoVoltar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				if (e.getSource() == BotaoVoltar2) {
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					ConsultaClientes();
				}
			}
		});
		BotaoVoltar2.setBounds(322, 433, 130, 33);
		getContentPane().add(BotaoVoltar2);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(35, 108, 744, 302);
		getContentPane().add(panel);

		setSize(820, 528);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

		BotaoSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == BotaoSalvar) {

				}
				if (CampoNome != null && CampoNome.getText().equals("") == true) {
					JOptionPane.showMessageDialog(null, "Digite o nome");
				} else if (CampoCpf_1 != null && CampoCpf_1.getText().equals(null) == true) {
					JOptionPane.showMessageDialog(null, "Digite o CPF");
				} else if (CampoEndereco != null && CampoEndereco.getText().equals("") == true) {
					JOptionPane.showMessageDialog(null, "Digite o endereço");
				} else if (CampoEmail != null && CampoEmail.getText().equals("") == true) {
					JOptionPane.showMessageDialog(null, "Digite o email");
				} else if (CampoTelefone_1 != null && CampoTelefone_1.getText().equals(null) == true) {
					JOptionPane.showMessageDialog(null, "Digite o telefone");
				} else {

					DB[EditarMatriz - 1][0] = CampoNome.getText();
					DB[EditarMatriz - 1][1] = CampoCpf_1.getText();
					DB[EditarMatriz - 1][2] = CampoEndereco.getText();
					DB[EditarMatriz - 1][3] = CampoEmail.getText();
					DB[EditarMatriz - 1][4] = CampoTelefone_1.getText();

					JOptionPane.showMessageDialog(null, "CLIENTE ATUALIZADO COM SUCESSO.");
					JPanel TrocarTela = null;
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					ConsultaClientes();

				}

			}

		});

	}

	public void Informacao() {

		setTitle("SOFTWARE - CADASTRO DE CLIENTES - v.1.0 - JAVA SWING");
		setSize(820, 528);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JTextArea txtrDisciplinaAlgoritmoE = new JTextArea();
		txtrDisciplinaAlgoritmoE.setEnabled(false);
		txtrDisciplinaAlgoritmoE.setEditable(false);
		txtrDisciplinaAlgoritmoE.setText(
				"\t\r\n\r\n\tEXERCICIO 51 - CADASTRO DE CLIENTES\r\n\r\n\tDISCIPLINA: ALGORITMO E LOGICA DE PROGRAMA\u00C7\u00C3O II\r\n\r\n\tCURSO: CI\u00CANCIA DA COMPUTA\u00C7\u00C3O\r\n\r\n\tIES: FACULDADE ALVORADA DE EDUCA\u00C7\u00C3O E TECNOLOGIA DE MARING\u00C1\r\n\r\n\tPROF.\u00BA ALTIERES DE MATOS\r\n\r\n\tAPLICA\u00C7\u00C3O DESENVOLVIDA EM JAVA SE, NO SOFTWARE ECLIPSE. 2.0 MARS\r\n\t ");
		txtrDisciplinaAlgoritmoE.setBackground(SystemColor.text);
		txtrDisciplinaAlgoritmoE.setRows(10);
		txtrDisciplinaAlgoritmoE.setBounds(111, 126, 592, 255);
		getContentPane().add(txtrDisciplinaAlgoritmoE);

		JLabel lblCadastroDeClientes = new JLabel("CADASTRO DE CLIENTES");
		lblCadastroDeClientes.setFont(new Font("Roboto Condensed", Font.PLAIN, 20));
		lblCadastroDeClientes.setBounds(304, 43, 206, 85);
		getContentPane().add(lblCadastroDeClientes);

		JButton btnVoltar3 = new JButton("VOLTAR");
		btnVoltar3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JPanel TrocarTela = null;
				TrocarTela = new JPanel();
				getContentPane().removeAll();
				getContentPane().add(TrocarTela);
				validate();
				TelaInicial();

			}
		});
		btnVoltar3.setBounds(581, 416, 121, 40);
		getContentPane().add(btnVoltar3);
		setResizable(false);
		setVisible(true);

	}

	public void Loading() {

		JLabel LOGO = new JLabel(new ImageIcon(getClass().getResource("cs.png")));
		LOGO.setBounds(342, 130, 128, 128);
		getContentPane().add(LOGO);

		final JLabel car = new JLabel("CASA");
		setTitle("SOFTWARE - CADASTRO DE CLIENTES - v.1.0 - JAVA SWING");
		setSize(820, 528);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);
		setVisible(true);

		final JLabel lblCarregandoAguarde = new JLabel("         CARREGANDO");
		lblCarregandoAguarde.setFont(new Font("Roboto Condensed", Font.PLAIN, 21));
		lblCarregandoAguarde.setBounds(298, 345, 216, 40);
		getContentPane().add(lblCarregandoAguarde);

		final JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(271, 284, 269, 35);
		getContentPane().add(progressBar);

		new Thread() {

			public void run() {

				for (int i = 0; i < 101; i++) {
					try {
						sleep(60);
						progressBar.setValue(i);

						if (progressBar.getValue() <= 40) {
							car.setText("CARREGANDO DADOS");

						} else if (progressBar.getValue() <= 70) {
							lblCarregandoAguarde.setText("CARREGANDO TABELAS");
						} else {
							lblCarregandoAguarde.setText("CARREGANDO SISTEMA");
						}

					} catch (InterruptedException ex) {
						Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);

					}

				}

				JPanel TrocarTela = null;
				TrocarTela = new JPanel();
				getContentPane().removeAll();
				getContentPane().add(TrocarTela);
				validate();

				TelaInicial();
				repaint();
			}

		}.start();

	}

	public void TelaInicial() {

		setTitle("SOFTWARE - CADASTRO DE CLIENTES - v.1.0 - JAVA SWING");
		setSize(820, 528);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);

		JPanel PainelCadastro = new JPanel();
		PainelCadastro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PainelCadastro.setBounds(38, 34, 735, 425);
		getContentPane().add(PainelCadastro);
		PainelCadastro.setLayout(null);

		JLabel LOGO = new JLabel(new ImageIcon(getClass().getResource("cs.png")));
		LOGO.setBounds(303, 68, 128, 128);
		PainelCadastro.add(LOGO);

		JLabel LabelCadastroDeClientes = new JLabel("CADASTRO DE CLIENTES");
		LabelCadastroDeClientes.setBounds(258, 225, 218, 26);
		PainelCadastro.add(LabelCadastroDeClientes);
		LabelCadastroDeClientes.setFont(new Font("Roboto Condensed", Font.PLAIN, 21));

		final JButton BotaoCadastrar = new JButton("CADASTRAR");
		BotaoCadastrar.setBounds(63, 314, 130, 40);
		PainelCadastro.add(BotaoCadastrar);

		final JButton BotaoSair = new JButton("SAIR");
		BotaoSair.setBounds(369, 314, 130, 40);
		PainelCadastro.add(BotaoSair);
		BotaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == BotaoSair) {
					System.exit(0);
				}
			}
		});

		final JButton BotaoConsultar = new JButton("CONSULTAR");
		BotaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				if (e.getSource() == BotaoConsultar) {
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					ConsultaClientes();
				}
			}
		});
		BotaoConsultar.setBounds(216, 314, 130, 40);
		PainelCadastro.add(BotaoConsultar);

		JButton btnINFO = new JButton("INFORMA\u00C7\u00D5ES");
		btnINFO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				TrocarTela = new JPanel();
				getContentPane().removeAll();
				getContentPane().add(TrocarTela);
				revalidate();
				repaint();
				Informacao();

			}
		});
		btnINFO.setBounds(522, 314, 130, 40);
		PainelCadastro.add(btnINFO);

		JMenuBar BarraSuperior = new JMenuBar();
		BarraSuperior.setBounds(650, 0, 70, 35);
		getContentPane().add(BarraSuperior);

		JMenuItem ItemHelp = new JMenuItem("AJUDA");
		ItemHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						" " + "Cadastro de Clientes | v.1.0" + "\n" + "\n * Para cadastrar um cliente"
								+ "\n Vá até a opção CADASTRAR, e preencha todos os campos. Clique no botão SALVAR"
								+ "\n Após o cadastro, os campos ficarão livre para um proximo cadastro."
								+ "\n * Para Consultar um cliente" + ""
								+ "\n Vá ao Menu Inicial, Clique na opção CONSULTAR, nele abrirá uma tabela com todos "
								+ "dados inseridos." + "\n * Para Excluir um cliente"
								+ "\n Clique no cliente desejado na tabela, e clique no botão EXCLUIR"
								+ "\n *  Para  Atualizar um cliente"
								+ "\n Clique no cliente desejado na tabela, e clique no botão ATUALIZAR"
								+ "\n Para Adicionar um Telefone"
								+ "\n Clique no cliente desejado na tabela, e clique no botão ADICIONAR TELEFONE");
			}
		});
		BarraSuperior.add(ItemHelp);

		Date data = new Date();
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formatar.format(data);

		Date hora = new Date();
		SimpleDateFormat formatarHora = new SimpleDateFormat("hh:mm");
		String horaFormatada = formatarHora.format(hora);

		JLabel DataAqui = new JLabel(dataFormatada);
		DataAqui.setBounds(90, 12, 74, 16);
		getContentPane().add(DataAqui);
		DataAqui.setBounds(90, 12, 111, 16);

		JLabel HoraAqui = new JLabel(horaFormatada);
		HoraAqui.setBounds(174, 12, 55, 16);
		getContentPane().add(HoraAqui);

		BotaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				if (e.getSource() == BotaoCadastrar) {
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					CadastroCliente();
				}
			}
		});
		setVisible(true);
	}

	public Main() {
		// Informacao();
		Loading();
		//TelaInicial();
		//ConsultaClientes();
		// CadastroCliente();
		// AtualizarClientes();
		// CadastrarTelefone();

	}

	public static void main(String[] args) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {

		}

		new Main();
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}