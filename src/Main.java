
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
import java.awt.Rectangle;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings({ "serial", "unused" })
public class Main extends JFrame implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	}

	static JButton Limpar = new JButton("LIMPAR");
	private JTextField CampoId;
	private static JTextField CampoNome;
	private static JTextField CampoEndereco;
	private static JLabel LabelCpf;
	private static JFormattedTextField CampoCpf;
	private static JTextField CampoEmail;
	private static JFormattedTextField CampoTelefone;

	private MaskFormatter setMascara(String mascara) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(mascara);
		} catch (java.text.ParseException ex) {
		}
		return mask;
	}
	//MATRIZ CLIENTES
	
	static int id = 0;
	static int clientes = 100;
	static int dados = 5;
	static String[][] DB = new String[clientes][dados];
	private JTable TabelaClientes;
	
/*
	addKeyListener(new KeyAdapter(){
		
		static void KeyPressed(KeyEvent e){
			int codigo = e.getKeyCode();
			int tecla = KeyEvent.VK_ENTER;
			if(codigo == tecla){
				System.out.println("Tecla Enter pressionada");
			}
		}});
	*/
	
	
	
	public void ConsultaClientes() {

		setVisible(true);
		setSize(820, 528);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("CONSULTA DE CLIENTES");
		getContentPane().setLayout(null);

		final JButton BotaoExcluir = new JButton("EXCLUIR");
		BotaoExcluir.setEnabled(false);
		BotaoExcluir.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/collapsed-rtl.gif")));
		BotaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == BotaoExcluir) {
					JOptionPane.showMessageDialog(null, "  NÃO HÁ REGISTRO PARA SER DELETADO.");
				}
			}
		});
		BotaoExcluir.setBounds(33, 420, 130, 33);
		getContentPane().add(BotaoExcluir);

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
		BotaoVoltar.setBounds(317, 420, 130, 33);
		getContentPane().add(BotaoVoltar);

		final JButton BotaoEditar = new JButton("ATUALIZAR");
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
		BotaoEditar.setBounds(175, 420, 130, 33);
		getContentPane().add(BotaoEditar);

		JLabel lblConsultarClientes = new JLabel("CONSULTAR CLIENTES");
		lblConsultarClientes.setFont(new Font("Dialog", Font.BOLD, 17));
		lblConsultarClientes.setBounds(300, 44, 214, 16);
		getContentPane().add(lblConsultarClientes);

		JScrollPane ScrolTabelaClientes = new JScrollPane();
		ScrolTabelaClientes.setBounds(33, 115, 744, 288);
		getContentPane().add(ScrolTabelaClientes);

		TabelaClientes = new JTable();
		TabelaClientes.setShowGrid(false);
		TabelaClientes.setShowHorizontalLines(false);
		ScrolTabelaClientes.setViewportView(TabelaClientes);
		TabelaClientes.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "NOME", "CPF", "ENDERECO", "EMAIL", "TELEFONE" }));

		TabelaClientes.getColumnModel().getColumn(0).setPreferredWidth(28);
		TabelaClientes.getColumnModel().getColumn(1).setPreferredWidth(257);
		TabelaClientes.getColumnModel().getColumn(2).setPreferredWidth(107);
		TabelaClientes.getColumnModel().getColumn(3).setPreferredWidth(134);
		TabelaClientes.getColumnModel().getColumn(4).setPreferredWidth(123);
		TabelaClientes.getColumnModel().getColumn(5).setPreferredWidth(116);

		for (int i = 0; i < id; i++) {
			DefaultTableModel valores = (DefaultTableModel) TabelaClientes.getModel();
			valores.addRow(new Object[] { i + 1, DB[i][0], DB[i][1], DB[i][2], DB[i][3], DB[i][4] });
		}

	}

	
	
	
	public void CadastroCliente() {

		
		getContentPane().add(Limpar);
		Limpar.setBounds(180, 433, 130, 33);
		Limpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				CampoNome.setText("");
				CampoCpf.setText("");
				CampoEndereco.setText("");
				CampoEmail.setText("");
				CampoTelefone.setText("");

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
		CampoTelefone_1.setBounds(497, 267, 156, 20);
		getContentPane().add(CampoTelefone_1);
		CampoTelefone_1.setColumns(10);

		CampoEmail = new JTextField();
		CampoEmail.setBounds(187, 267, 217, 20);
		getContentPane().add(CampoEmail);
		CampoEmail.setColumns(10);

		CampoId = new JTextField();
		CampoId.setEditable(false);
		CampoId.setMargin(new Insets(0, 7, 0, 0));
		CampoId.setBounds(new Rectangle(3, 0, 0, 0));
		CampoId.setBounds(171, 180, 29, 20);
		getContentPane().add(CampoId);
		CampoId.setColumns(10);
		CampoId.setText(String.valueOf(id + 1));

		JLabel LabelEmail = new JLabel("EMAIL");
		LabelEmail.setBounds(138, 270, 46, 14);
		getContentPane().add(LabelEmail);

		CampoEndereco = new JTextField();
		CampoEndereco.setBounds(429, 224, 224, 20);
		getContentPane().add(CampoEndereco);
		CampoEndereco.setColumns(10);

		JLabel LabelNome = new JLabel("NOME");
		LabelNome.setBounds(233, 183, 40, 14);
		getContentPane().add(LabelNome);

		CampoNome = new JTextField();
		CampoNome.setBounds(283, 180, 370, 20);
		getContentPane().add(CampoNome);
		CampoNome.setColumns(10);

		JLabel LabelTelefone = new JLabel("TELEFONE");
		LabelTelefone.setBounds(429, 270, 65, 14);
		getContentPane().add(LabelTelefone);
		final JFormattedTextField CampoCpf_1 = new JFormattedTextField((setMascara(" ###.###.###-##")));
		CampoCpf_1.setToolTipText("");
		CampoCpf_1.setBounds(177, 224, 159, 20);
		getContentPane().add(CampoCpf_1);
		CampoCpf_1.setColumns(10);

		JLabel ImagemCadastro = new JLabel(new ImageIcon(getClass().getResource("cadastro.png")));
		ImagemCadastro.setBounds(98, 23, 65, 57);
		getContentPane().add(ImagemCadastro);

		CampoCpf = new JFormattedTextField();

		JLabel LabelCadastroDeCliente = new JLabel("CADASTRO DE CLIENTE");
		LabelCadastroDeCliente.setFont(new Font("Dialog", Font.BOLD, 17));
		LabelCadastroDeCliente.setBounds(303, 49, 208, 14);
		getContentPane().add(LabelCadastroDeCliente);

		final JButton BotaoSalvar = new JButton("SALVAR");
		BotaoSalvar.setIcon(
				new ImageIcon(Main.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		BotaoSalvar.setBounds(35, 433, 130, 33);
		getContentPane().add(BotaoSalvar);

		CampoTelefone = new JFormattedTextField();

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
						revalidate();
						repaint();
						CadastroCliente();

					}
				}
			}

		});

	}

	public void AtualizarClientes() {

		getContentPane().add(Limpar);
		Limpar.setBounds(180, 433, 130, 33);
		Limpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				CampoNome.setText("");
				CampoCpf.setText("");
				CampoEndereco.setText("");
				CampoEmail.setText("");
				CampoTelefone.setText("");

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
		CampoTelefone_1.setBounds(497, 267, 156, 20);
		getContentPane().add(CampoTelefone_1);
		CampoTelefone_1.setColumns(10);

		CampoEmail = new JTextField();
		CampoEmail.setBounds(187, 267, 217, 20);
		getContentPane().add(CampoEmail);
		CampoEmail.setColumns(10);
		
		int LinhaTabela = TabelaClientes.getSelectedRow();
		final int EditarMatriz = (int) TabelaClientes.getModel().getValueAt(LinhaTabela, 0);
		
		CampoId = new JTextField();
		CampoId.setEditable(false);
		CampoId.setMargin(new Insets(0, 7, 0, 0));
		CampoId.setBounds(new Rectangle(3, 0, 0, 0));
		CampoId.setBounds(171, 180, 29, 20);
		getContentPane().add(CampoId);
		CampoId.setColumns(10);
		CampoId.setText(String.valueOf(LinhaTabela+1));
		
		JLabel LabelEmail = new JLabel("EMAIL");
		LabelEmail.setBounds(138, 270, 46, 14);
		getContentPane().add(LabelEmail);

		
		CampoEndereco = new JTextField();
		CampoEndereco.setBounds(429, 224, 224, 20);
		getContentPane().add(CampoEndereco);
		CampoEndereco.setColumns(10);

		JLabel LabelNome = new JLabel("NOME");
		LabelNome.setBounds(233, 183, 40, 14);
		getContentPane().add(LabelNome);

		CampoNome = new JTextField();
		CampoNome.setBounds(283, 180, 370, 20);
		getContentPane().add(CampoNome);
		CampoNome.setColumns(10);
		CampoNome.setText(DB[Integer.parseInt(CampoId.getText())-1][0]);
		
		JLabel LabelTelefone = new JLabel("TELEFONE");
		LabelTelefone.setBounds(429, 270, 65, 14);
		getContentPane().add(LabelTelefone);
		
		final JFormattedTextField CampoCpf_1 = new JFormattedTextField((setMascara(" ###.###.###-##")));
		CampoCpf_1.setToolTipText("");
		CampoCpf_1.setBounds(177, 224, 159, 20);
		getContentPane().add(CampoCpf_1);
		CampoCpf_1.setColumns(10);

		JLabel ImagemCadastro = new JLabel(new ImageIcon(getClass().getResource("cadastro.png")));
		ImagemCadastro.setBounds(98, 23, 65, 57);
		getContentPane().add(ImagemCadastro);

		CampoCpf = new JFormattedTextField();

		JLabel LabelCadastroDeCliente = new JLabel("EDITAR CLIENTES");
		LabelCadastroDeCliente.setFont(new Font("Dialog", Font.BOLD, 17));
		LabelCadastroDeCliente.setBounds(329, 49, 156, 14);
		getContentPane().add(LabelCadastroDeCliente);

		final JButton BotaoSalvar = new JButton("SALVAR");
		BotaoSalvar.setIcon(
				new ImageIcon(Main.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		BotaoSalvar.setBounds(35, 433, 130, 33);
		getContentPane().add(BotaoSalvar);

		CampoTelefone = new JFormattedTextField();

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
		LOGO.setBounds(144, 50, 128, 128);
		PainelCadastro.add(LOGO);

		JLabel LabelCadastroDeClientes = new JLabel("CADASTRO DE CLIENTES");
		LabelCadastroDeClientes.setBounds(108, 201, 212, 16);
		PainelCadastro.add(LabelCadastroDeClientes);
		LabelCadastroDeClientes.setFont(new Font("Dialog", Font.BOLD, 17));

		final JButton BotaoCadastrar = new JButton("CADASTRAR");
		BotaoCadastrar.setBounds(66, 254, 130, 33);
		PainelCadastro.add(BotaoCadastrar);

		final JButton BotaoSair = new JButton("SAIR");
		BotaoSair.setBounds(228, 254, 130, 33);
		PainelCadastro.add(BotaoSair);
		BotaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == BotaoSair) {
					System.exit(0);
				}
			}
		});

		Box CaixaSoftware = Box.createVerticalBox();
		CaixaSoftware.setBounds(410, 142, 301, 168);
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
				" \r\n  * USER INTERFACE\r\n\r\n - Ci\u00EAncia da Computa\u00E7\u00E3o - 3\u00BA Semestre.\r\n - Algoritmos e Logica  de  Programa\u00E7\u00E3o  II\r\n - Prof.\u00BA  Altieres de Mattos\r\n - Acad\u00EAmico: Tharlyson Jr. Santos Ribeiro\r\n ");

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
		BotaoConsultar.setBounds(66, 305, 130, 33);
		PainelCadastro.add(BotaoConsultar);
		
		JMenuBar BarraSuperior = new JMenuBar();
		BarraSuperior.setBounds(650, 0, 60, 35);
		getContentPane().add(BarraSuperior);
		
		JMenuItem ItemHelp = new JMenuItem("HELP");
		ItemHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null," "
						+ "Cadastro de Clientes | v.1.0"
						+ "\n"
						+ "\n Com esta versão, é possivel:"
						+ "\n * Cadastrar cem clientes"
						+ "\n * Atualizar clientes"
						+ "\n * Consultar clientes cadastrados"
						+ "");
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

		TelaInicial();
		//ConsultaClientes();
		//CadastroCliente();
		//EditarClientes();

	}

	public static void main(String[] args) {

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