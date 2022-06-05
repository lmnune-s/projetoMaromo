package com.company.view;

import com.company.model.Empregado;
import com.company.model.GerenciarEmpregado;
import com.company.model.Parametros;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmpregadoGUI {
    private JTextField textFieldNomeEmpregado;
    private JTextField textFieldCodigoEmpregado;
    private JTextField textFieldSetor;
    private JTextField textFieldSalarioBruto;
    private JButton calcularRecolhimentoINSSButton;
    private JButton cadastrarButton;
    private JButton apresentarEmpregadosButton;
    private JLabel LabelCodigoEmpregado;
    private JLabel LabelNomeEmpregado;
    private JLabel LabelSetor;
    private JLabel LabelSalarioBruto;
    private JLabel LabelRecolhimento;
    private JLabel LabelReais;
    private JLabel LabelValorRecolhido;
    private JPanel JPanelTelaCadastro;
    private JTable tableGerenciarEmp;

    public Empregado empregado;
    public GerenciarEmpregado ge = new GerenciarEmpregado();
    public Parametros inss;
//    Integer i=0;


    public JPanel getPanelTelaCadastro(){
        return JPanelTelaCadastro;
    }

    public EmpregadoGUI(){
        apresentarEmpregadosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirEmpregados();
            }
        });
        calcularRecolhimentoINSSButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CalcularRecolhimentoInss();
            }
        });
        cadastrarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                adicionarEmpregado();
            }
        });
    }

    public void CalcularRecolhimentoInss() {
        try{
            empregado = new Empregado();
            inss = new Parametros();
            Integer codigoEmp = Integer.parseInt(textFieldCodigoEmpregado.getText());
            String nomeEmp = textFieldNomeEmpregado.getText();
            String setorEmp = textFieldSetor.getText();
            Double salarioBruto = Double.parseDouble(textFieldSalarioBruto.getText());
            empregado.setCodigoEmpregado(codigoEmp);
            empregado.setNomeEmpregado(nomeEmp);
            empregado.setSetor(setorEmp);
            empregado.setSalarioBruto(salarioBruto);
            empregado.setRecInss(valorRecolhimentoInss(salarioBruto));
            LabelValorRecolhido.setText(String.valueOf(empregado.getRecInss()));


        }catch (Exception e){
            informativeMessage();
            System.out.println(e);
        }
    }

    public double valorRecolhimentoInss(double salarioBruto)
    {
        double recolhimento = 0.0;

        if (salarioBruto <= inss.LIMITEFAIXA1){
            return recolhimento = salarioBruto * inss.FAIXA1;
        }

        if (salarioBruto <= inss.LIMITEFAIXA2){
            recolhimento += inss.LIMITEFAIXA1* inss.FAIXA1;
            return recolhimento += (salarioBruto - inss.LIMITEFAIXA1) * inss.FAIXA2;
        }

        if (salarioBruto <= inss.LIMITEFAIXA3){
            recolhimento += inss.LIMITEFAIXA1* inss.FAIXA1;
            recolhimento += (inss.LIMITEFAIXA2-inss.LIMITEFAIXA1) * inss.FAIXA2;
            return recolhimento += (salarioBruto - inss.LIMITEFAIXA2) * inss.FAIXA3;
        }

        if (salarioBruto <= inss.LIMITEFAIXA4){
            recolhimento += inss.LIMITEFAIXA1* inss.FAIXA1;
            recolhimento += (inss.LIMITEFAIXA2-inss.LIMITEFAIXA1) * inss.FAIXA2;
            recolhimento += (inss.LIMITEFAIXA3-inss.LIMITEFAIXA2) * inss.FAIXA3;
            return recolhimento += (salarioBruto- inss.LIMITEFAIXA3) * inss.FAIXA4;
        }

        recolhimento += inss.LIMITEFAIXA1* inss.FAIXA1;
        recolhimento += (inss.LIMITEFAIXA2-inss.LIMITEFAIXA1) * inss.FAIXA2;
        recolhimento += (inss.LIMITEFAIXA3-inss.LIMITEFAIXA2) * inss.FAIXA3;
        recolhimento += (inss.LIMITEFAIXA4-inss.LIMITEFAIXA3) * inss.FAIXA4;
        return recolhimento;
    }

    public void abrirEmpregados(){
        try {
            JFrame gerenciarEmpregado = new JFrame();
            gerenciarEmpregado.setContentPane(new GerenciarEmpregadosGUI().getPanelEmpregados());
            gerenciarEmpregado.setSize(500,350);
            gerenciarEmpregado.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adicionarEmpregado(){
        if(verificaInputs()){
            ge.adicionarEmpregado(empregado);
            ge.listaTodosEmpregados();
            LimparCampos();
        }
    }

    public void LimparCampos(){
        textFieldCodigoEmpregado.setText("");
        textFieldNomeEmpregado.setText("");
        textFieldSetor.setText("");
        textFieldSalarioBruto.setText("");
        LabelValorRecolhido.setText("");
    }

    public boolean verificaInputs(){
        if ((empregado.getNomeEmpregado() == null) && (empregado.getSetor()==null)){
            errorMessage();
            return false;
        }
        if (empregado == null){
            errorMessage();
            return false;
        }
        return true;
    }

    public static void successMessage(){
        JOptionPane.showMessageDialog(null,
                "Empregado cadastrado com sucesso!",
                "Empregado cadastrado",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void errorMessage(){
        JOptionPane.showMessageDialog(null,
                "Por favor, preencha todos os campos!",
                "Preenchimento de Campos",
                JOptionPane.ERROR_MESSAGE);
    }

    public void informativeMessage(){
        JOptionPane.showMessageDialog(null,
                "Verifique se as informações estão corretas!",
                "Informações inválidas",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
