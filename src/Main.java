
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import java.awt.SystemColor;

@SuppressWarnings({ "serial" })
public class Main extends JFrame implements ActionListener {

	public void actionPerformed(ActionEvent e) {

	}

	String nome;
	String email;
	String telefone;
	String endereço;
	int RG;
	int CPF;

	private JPanel PainelPesquisa;
	private JTable TabelaClientes;
	private JScrollPane ScrolPainel;
	private JTextField CampoId;
	private JTextField CampoNome;
	private JTextField CampoEndereco;
	private JLabel LabelCpf;
	private JFormattedTextField CampoCpf;
	private JTextField CampoEmail;
	private JFormattedTextField CampoTelefone;
	private JTextField CampoPesquisa;
	private JTextField textField;
	private JTextField CampoConsulta;
	private JTable table;
	private JTextField textField_1;

	private MaskFormatter setMascara(String mascara) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(mascara);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}

	public void ConsultaClientes() {

		setVisible(true);
		setSize(720, 428);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("CONSULTA DE CLIENTES");
		getContentPane().setLayout(null);

		final JButton BotaoExcluir = new JButton("EXCLUIR");
		BotaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == BotaoExcluir) {
					JOptionPane.showMessageDialog(null, "  NÃO HÁ REGISTRO PARA SER DELETADO.");
				}
			}
		});
		BotaoExcluir.setBounds(562, 86, 98, 26);
		getContentPane().add(BotaoExcluir);

		final JButton BotaoVoltar = new JButton("<");
		BotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				if (e.getSource() == BotaoVoltar) {
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					CadastroCliente();
				}
			}
		});
		BotaoVoltar.setBounds(12, 12, 41, 26);
		getContentPane().add(BotaoVoltar);

		JLabel lblConsultarClientes = new JLabel("CONSULTAR CLIENTES");
		lblConsultarClientes.setFont(new Font("Dialog", Font.BOLD, 17));
		lblConsultarClientes.setBounds(250, 38, 214, 16);
		getContentPane().add(lblConsultarClientes);

		JLabel lblPesquisar = new JLabel("PESQUISAR");
		lblPesquisar.setBounds(73, 91, 72, 16);
		getContentPane().add(lblPesquisar);

		JScrollPane ScrolTabela = new JScrollPane();
		ScrolTabela.setBounds(23, 131, 665, 244);
		getContentPane().add(ScrolTabela, BorderLayout.CENTER);

		table = new JTable();
		table.setShowHorizontalLines(false);
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null }, },
				new String[] { "ID", "NOME", "CPF", "ENDERE\u00C7O", "EMAIL", "TELEFONE" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(28);
		table.getColumnModel().getColumn(1).setPreferredWidth(174);
		table.getColumnModel().getColumn(2).setPreferredWidth(98);
		table.getColumnModel().getColumn(3).setPreferredWidth(128);
		table.getColumnModel().getColumn(4).setPreferredWidth(111);
		table.getColumnModel().getColumn(5).setPreferredWidth(126);
		ScrolTabela.setViewportView(table);

		JScrollPane ScrolPesquisa = new JScrollPane();
		ScrolPesquisa.setBounds(163, 86, 259, 26);
		getContentPane().add(ScrolPesquisa);

		textField_1 = new JTextField();
		ScrolPesquisa.setViewportView(textField_1);
		textField_1.setColumns(10);
	}

	public void CadastroCliente() {

		JLabel ImagemCadastro = new JLabel(new ImageIcon(getClass().getResource("cadastro.png")));
		ImagemCadastro.setBounds(100, 17, 65, 57);
		getContentPane().add(ImagemCadastro);

		CampoEndereco = new JTextField();
		CampoEndereco.setBounds(362, 155, 268, 20);
		getContentPane().add(CampoEndereco);
		CampoEndereco.setColumns(10);

		JLabel LabelEndereco = new JLabel("ENDERE\u00C7O");
		LabelEndereco.setBounds(288, 158, 81, 14);
		getContentPane().add(LabelEndereco);

		CampoNome = new JTextField();
		CampoNome.setBounds(216, 111, 414, 20);
		getContentPane().add(CampoNome);
		CampoNome.setColumns(10);

		CampoCpf = new JFormattedTextField();
		JFormattedTextField CampoCpf = new JFormattedTextField((setMascara(" ###.###.###-##")));
		CampoCpf.setToolTipText("");
		CampoCpf.setBounds(110, 155, 159, 20);
		getContentPane().add(CampoCpf);
		CampoCpf.setColumns(10);

		LabelCpf = new JLabel("CPF");
		LabelCpf.setBounds(71, 158, 29, 14);
		getContentPane().add(LabelCpf);

		JLabel LabelCadastroDeCliente = new JLabel("CADASTRO DE CLIENTE");
		LabelCadastroDeCliente.setFont(new Font("Dialog", Font.BOLD, 17));
		LabelCadastroDeCliente.setBounds(273, 37, 208, 14);
		getContentPane().add(LabelCadastroDeCliente);

		JLabel LabelEmail = new JLabel("EMAIL");
		LabelEmail.setBounds(71, 195, 46, 14);
		getContentPane().add(LabelEmail);

		JSeparator separator = new JSeparator();
		separator.setBounds(71, 86, 559, 2);
		getContentPane().add(separator);

		JLabel LabelTelefone = new JLabel("TELEFONE");
		LabelTelefone.setBounds(362, 195, 65, 14);
		getContentPane().add(LabelTelefone);

		CampoEmail = new JTextField();
		CampoEmail.setBounds(120, 192, 217, 20);
		getContentPane().add(CampoEmail);
		CampoEmail.setColumns(10);

		final JButton BotaoSalvar = new JButton("SALVAR");
		BotaoSalvar.setIcon(
				new ImageIcon(Main.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		BotaoSalvar.setBounds(71, 254, 119, 48);
		getContentPane().add(BotaoSalvar);

		BotaoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == BotaoSalvar) {
					JOptionPane.showMessageDialog(null, "  CLIENTE NÃO PODE SER CADASTRADO.");
				}
			}
		});

		CampoTelefone = new JFormattedTextField();
		JFormattedTextField CampoTelefone = new JFormattedTextField((setMascara(" (##)####-####")));
		CampoTelefone.setToolTipText("");
		CampoTelefone.setBounds(430, 192, 152, 20);
		getContentPane().add(CampoTelefone);
		CampoTelefone.setColumns(10);

		final JButton BotaoListar = new JButton("LISTAR");
		BotaoListar.setIcon(
				new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/collapsed-rtl.gif")));
		BotaoListar.setBounds(216, 254, 119, 48);
		getContentPane().add(BotaoListar);
		BotaoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel TrocarTela = null;
				if (e.getSource() == BotaoListar) {
					TrocarTela = new JPanel();
					getContentPane().removeAll();
					getContentPane().add(TrocarTela);
					revalidate();
					repaint();
					ConsultaClientes();
				}
			}

		});

		JLabel LabelNome = new JLabel("NOME");
		LabelNome.setBounds(166, 114, 40, 14);
		getContentPane().add(LabelNome);

		JLabel LabelId = new JLabel("ID");
		LabelId.setVerticalAlignment(SwingConstants.BOTTOM);
		LabelId.setBounds(71, 111, 23, 17);
		getContentPane().add(LabelId);

		CampoId = new JTextField();
		CampoId.setEnabled(false);
		CampoId.setBounds(104, 111, 40, 20);
		getContentPane().add(CampoId);
		CampoId.setColumns(10);

		setTitle("CADASTRE UM CLIENTE");
		getContentPane().setLayout(null);

		final JButton BotaoVoltar2 = new JButton("<");
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
		BotaoVoltar2.setBounds(12, 12, 46, 26);
		getContentPane().add(BotaoVoltar2);

		setSize(720, 428);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	public void TelaInicial() {

		setTitle("SOFTWARE - CADASTRO DE CLIENTES - v.1.0 - JAVA SWING");
		setSize(720, 428);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);

		JPanel PainelCadastro = new JPanel();
		PainelCadastro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PainelCadastro.setBounds(28, 26, 660, 346);
		getContentPane().add(PainelCadastro);
		PainelCadastro.setLayout(null);

		JLabel LOGO = new JLabel(new ImageIcon(getClass().getResource("cs.png")));
		LOGO.setBounds(76, 12, 128, 128);
		PainelCadastro.add(LOGO);

		JLabel LabelCadastroDeClientes = new JLabel("CADASTRO DE CLIENTES");
		LabelCadastroDeClientes.setBounds(49, 152, 212, 16);
		PainelCadastro.add(LabelCadastroDeClientes);
		LabelCadastroDeClientes.setFont(new Font("Dialog", Font.BOLD, 17));

		final JButton BotaoCadastrar = new JButton("CADASTRAR");
		BotaoCadastrar.setBounds(89, 200, 130, 33);
		PainelCadastro.add(BotaoCadastrar);

		final JButton BotaoSair = new JButton("SAIR");
		BotaoSair.setBounds(89, 245, 130, 33);
		PainelCadastro.add(BotaoSair);
		BotaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == BotaoSair) {
					System.exit(0);
				}
			}
		});

		Box CaixaSoftware = Box.createVerticalBox();
		CaixaSoftware.setBounds(359, 50, 254, 256);
		PainelCadastro.add(CaixaSoftware);
		CaixaSoftware.setFont(new Font("Dialog", Font.PLAIN, 12));
		CaixaSoftware.setToolTipText("");
		CaixaSoftware.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "SOBRE O SOFTWARE",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(51, 51, 51)));

		JTextPane Info = new JTextPane();
		Info.setEditable(false);
		Info.setFont(new Font("Dialog", Font.PLAIN, 12));
		Info.setBackground(SystemColor.control);
		CaixaSoftware.add(Info);
		Info.setText(
				" \r\n \r\n\r\n  * USER INTERFACE\r\n\r\n - Ci\u00EAncia da Computa\u00E7\u00E3o - 3\u00BA Semestre.\r\n - Algoritmos e Logica  de  Programa\u00E7\u00E3o  II\r\n - Prof.\u00BA  Altieres de Mattos\r\n - Acad\u00EAmico: Tharlyson Jr. Santos Ribeiro\r\n ");

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

		TelaInicial();
		// ConsultaClientes();
		// CadastroCliente();

	}

	public static void main(String[] args) {

		new Main();
	}
}
