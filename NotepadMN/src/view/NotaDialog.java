package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import modelo.Nota;
import modelo.Usuario;
import operacao.Cadastro;
import operacao.Login;
import operacao.NotaOp;

public class NotaDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JLabel lbNota;
	private JLabel lbNome;
	private JTextArea tfNota;
	private JTextField tfNome;
	private JList notas;

	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnNovo;
	private Usuario user;

	public NotaDialog(Frame parent){
		super(parent, "Nota", true);
		user = Login.getLogged();
		NotaOp.buscarNotas(user);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		cs.fill = GridBagConstraints.HORIZONTAL;

		lbNome = new JLabel("Nome da nota: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(lbNome, cs);

		tfNome = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(tfNome, cs);
		
		tfNota = new JTextArea();
		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 2;
		panel.add(tfNota, cs);
		tfNota.setPreferredSize( new Dimension( 200, 200 ) );
		

		
		
		notas = new JList(NotaOp.buscarNotas(user));
		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 20;
		panel.add(notas, cs);
		notas.setPreferredSize( new Dimension( 200, 200 ) );
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(notas);
		
		notas.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {// Double-click detected
		        	Nota c = NotaOp.buscaNota((String) notas.getSelectedValue(),user);
		        	tfNota.setText(c.getNota());
		        	tfNome.setText(c.getNome());
		        	tfNome.disable();
		        }
		    }
		});
		

		btnSalvar = new JButton("Salvar");

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Nota c = new Nota(tfNome.getText(),tfNota.getText());
				NotaOp.salvarNota(c, user);
				notas.setListData(NotaOp.buscarNotas(user));
				tfNome.disable();
			}
		}); 

		btnCancelar = new JButton("Sair");

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		btnNovo = new JButton("Novo");

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfNome.enable();
				tfNome.setText("");
				tfNota.setText("");
			}
		}); 

		
		JPanel bp = new JPanel();
		bp.add(notas);
		bp.add(btnCancelar);
		bp.add(btnSalvar);
		bp.add(btnNovo);

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
}
