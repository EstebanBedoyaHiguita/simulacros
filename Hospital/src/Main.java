import DataBase.ConfiDB;
import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        PacienteController objPacienteController = new PacienteController();
        EspecialidadController objEspecialidadControler = new EspecialidadController();
        MedicoController objMedicoController = new MedicoController();
        CitaController objCitaController = new CitaController();
        String opcion1 = "";

        do {
            opcion1 = JOptionPane.showInputDialog("""
                    1.Paciente
                    2.Doctor
                    3.Citas
                    4.Especialidades
                    5.Salir
                    """);

            switch (opcion1){
                case "1":
                    String opcion2 = "";

                    do {
                        opcion2 = JOptionPane.showInputDialog("""
                    1.Crear Paciente
                    2.Editar Paciente
                    3.Buscar Paciente
                    4.Eliminar
                    5.Lista Pacientes
                    6.Salir
                    """);

                        switch (opcion2){
                            case "1":
                                objPacienteController.create();
                                break;
                            case "2":
                                objPacienteController.edit();
                                break;
                            case "3":
                                String opcion3 = "";

                                do {
                                    opcion3 = JOptionPane.showInputDialog("""
                                                1.Buscar por nombre
                                                2.Buscar por Id
                                                3.Salir
                                              
                                                """);

                                    switch (opcion3){
                                        case "1":
                                            objPacienteController.buscar();
                                            break;
                                        case "2":
                                            objPacienteController.buscarId();
                                            break;
                                    }
                                }while (!opcion3.equals("3"));
                                break;
                            case "4":
                                objPacienteController.delete();
                                break;
                            case "5":
                                objPacienteController.getAll();
                                break;
                        }
                    }while (!opcion2.equals("6"));
                    break;

                case "2":
                    String opcion5 = "";

                    do {
                        opcion5 = JOptionPane.showInputDialog("""
                                1.Crear Doctor
                                2.Editar Doctor
                                3.Buscar Doctor
                                4.Eliminar Doctor
                                5.Lista de Doctor
                                6.Salir
                                """);

                        switch (opcion5) {
                            case "1":
                                objMedicoController.create();
                                break;
                            case "2":
                                objMedicoController.edit();
                                break;
                            case "3":
                                String opcion6 = "";

                                do {
                                    opcion6 = JOptionPane.showInputDialog("""
                                                1.Buscar por nombre
                                                2.Buscar por Id
                                                3.Salir
                                              
                                                """);

                                    switch (opcion6){
                                        case "1":
                                            objMedicoController.buscar();
                                            break;
                                        case "2":
                                            objMedicoController.buscarId();
                                            break;
                                    }
                                }while (!opcion6.equals("3"));
                                break;
                            case "4":
                                objMedicoController.delete();
                                break;
                            case "5":
                                objMedicoController.getAll();
                                break;
                        }
                    }while (!opcion5.equals("6"));
                    break;
                case "3":

                    String opcion7 = "";

                    do {
                        opcion7 = JOptionPane.showInputDialog("""
                                1.Crear Cita
                                2.Editar Cita
                                3.Buscar Cita
                                4.Eliminar Cita
                                5.Lista de Citas
                                6.Salir
                                """);

                        switch (opcion7) {
                            case "1":
                                objCitaController.create();
                                break;
                            case "2":
                                objCitaController.edit();
                                break;
                            case "3":
                                String opcion8 = "";

                                do {
                                    opcion8 = JOptionPane.showInputDialog("""
                                                1.Buscar por nombre
                                                2.Buscar por Id
                                                3.Salir
                                              
                                                """);

                                    switch (opcion8){
                                        case "1":
                                            objCitaController.buscar();
                                            break;
                                        case "2":
                                            objMedicoController.buscarId();
                                            break;
                                    }
                                }while (!opcion8.equals("3"));
                                break;
                            case "4":
                                objCitaController.delete();
                                break;
                            case "5":
                                objCitaController.getAll();
                                break;
                        }
                    }while (!opcion7.equals("6"));
                    break;

                case "4":
                    String opcion4 = "";

                    do {
                        opcion4 = JOptionPane.showInputDialog("""
                                1.Crear Especialidad
                                2.Editar Especialidad
                                3.Buscar Especialidad
                                4.Eliminar Especialidad
                                5.Lista de Especialidades
                                6.Salir
                                """);

                        switch (opcion4) {
                            case "1":
                                objEspecialidadControler.create();
                                break;
                            case "2":
                                objEspecialidadControler.edit();
                                break;
                            case "3":
                                objEspecialidadControler.buscar();
                                break;
                            case "4":
                                objEspecialidadControler.delete();
                                break;
                            case "5":
                                objEspecialidadControler.getAll();
                                break;
                        }
                    }while (!opcion4.equals("6"));
                    break;
            }
        }while (!opcion1.equals("5"));




    }
}