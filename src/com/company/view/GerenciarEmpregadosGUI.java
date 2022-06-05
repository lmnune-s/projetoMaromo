package com.company.view;

import com.company.model.Empregado;
import com.company.model.GerenciarEmpregado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GerenciarEmpregadosGUI {
    private JPanel panelEmpregados;
    private JTable tableGerenciarEmp;
    private JScrollPane scrollPane1;
    public List<Empregado> listaEmpregados;

    public static GerenciarEmpregado ge;

    public JPanel getPanelEmpregados() {
        return panelEmpregados;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String header[] = {"Codigo", "Nome", "Setor", "Salario", "Recolhimento"};
        DefaultTableModel model = new DefaultTableModel(0,4);
        model.setColumnIdentifiers(header);
        tableGerenciarEmp = new JTable(model);
        List<Empregado> empregados = GerenciarEmpregado.listaEmpregados;
        for (Empregado emp : empregados) {
            Object[] row = {emp.getCodigoEmpregado(), emp.getNomeEmpregado(), emp.getSetor(),
                    emp.getSalarioBruto(), emp.getRecInss()
            };
            model.addRow(row);
        }
    }

}
