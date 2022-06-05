package com.company.model;

import com.company.view.EmpregadoGUI;

import java.util.ArrayList;
import java.util.List;

public class GerenciarEmpregado {
    public static List<Empregado> listaEmpregados = new ArrayList<Empregado>();

    public void adicionarEmpregado(Empregado empregado){
        if(verificarExistencia(empregado)){
            listaEmpregados.add(empregado);
            EmpregadoGUI.successMessage();
        }else {
            EmpregadoGUI.errorMessage();
            System.out.println("Error: Usuário já cadastrado!");
        }
    }

    public ArrayList listaTodosEmpregados(){
        for (Empregado emp: listaEmpregados) {
            System.out.println(emp);
        }
        return (ArrayList) listaEmpregados;
    }

    public static boolean verificarExistencia(Empregado empregado){
        for (Empregado emp: listaEmpregados) {
            if(emp.getCodigoEmpregado() == (empregado.getCodigoEmpregado())){
                System.out.println("Usuário já cadastrado!");
                return false;
            }
        }
        return true;
    }

    public GerenciarEmpregado(List<Empregado> listaEmpregados) {
        this.listaEmpregados = listaEmpregados;
    }

    public GerenciarEmpregado() {}

}
