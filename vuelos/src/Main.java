import Data_base.ConfiDB;
import controller.Avion_Controller;
import controller.Pasajero_Controller;
import controller.Reservacion_Controller;
import controller.Vuelo_Controller;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Pasajero_Controller objPasajeroController = new Pasajero_Controller();
        Avion_Controller objAvionController = new Avion_Controller();
        Vuelo_Controller objVueloController = new Vuelo_Controller();
        Reservacion_Controller objReservasController = new Reservacion_Controller();

        String opcion1 = "";

        do {
            opcion1 = JOptionPane.showInputDialog("""
                    1.Pasajero
                    2.Avion
                    3.Vuelos
                    4.Reservaciones
                    5.Salir
                    """);

            switch (opcion1){
                case "1":
                    String opcion2 = "";

                    do {
                        opcion2 = JOptionPane.showInputDialog("""
                    1.Crear pasajero
                    2.Editar pasajero
                    3.Buscar pasajero1
                    4.Eliminar pasajero
                    5.Lista pasajeros
                    6.Salir
                    """);

                        switch (opcion2){
                            case "1":
                                objPasajeroController.create();
                                break;
                            case "2":
                                objPasajeroController.edit();
                                break;
                            case "3":
                                String opcion3 = "";

                                do {
                                    opcion3 = JOptionPane.showInputDialog("""
                                                1.Buscar por nombre
                                                2.Buscar por Documento
                                                3.Salir
                                              
                                                """);

                                    switch (opcion3){
                                        case "1":
                                            objPasajeroController.buscarname();
                                            break;
                                        case "2":
                                            objPasajeroController.buscarDocumento();
                                            break;
                                    }
                                }while (!opcion3.equals("3"));
                                break;
                            case "4":
                                objPasajeroController.delete();
                                break;
                            case "5":
                                objPasajeroController.getAll();
                                break;
                        }
                    }while (!opcion2.equals("6"));
                    break;

                case "2":
                    String opcion5 = "";

                    do {
                        opcion5 = JOptionPane.showInputDialog("""
                                1.Crear avión
                                2.Editar avión
                                3.Buscar avión
                                4.Eliminar avión
                                5.Lista de avión
                                6.Salir
                                """);

                        switch (opcion5) {
                            case "1":
                                objAvionController.create();
                                break;
                            case "2":
                                objAvionController.edit();
                                break;
                            case "3":
                                String opcion6 = "";

                                do {
                                    opcion6 = JOptionPane.showInputDialog("""
                                                1.Buscar por modelo
                                                2.Buscar por Id
                                                3.Salir
                                              
                                                """);

                                    switch (opcion6){
                                        case "1":
                                            objAvionController.buscarModelo();
                                            break;
                                        case "2":
                                            objAvionController.buscarId();
                                            break;
                                    }
                                }while (!opcion6.equals("3"));
                                break;
                            case "4":
                                objAvionController.delete();
                                break;
                            case "5":
                                objAvionController.getAll();
                                break;
                        }
                    }while (!opcion5.equals("6"));
                    break;
                case "3":

                    String opcion7 = "";

                    do {
                        opcion7 = JOptionPane.showInputDialog("""
                                1.Crear Vuelo
                                2.Editar Vuelo
                                3.Buscar Vuelo
                                4.Eliminar Vuelo
                                5.Lista de Vuelos
                                6.Salir
                                """);

                        switch (opcion7) {
                            case "1":
                                objVueloController.create();
                                break;
                            case "2":
                                objVueloController.edit();
                                break;
                            case "3":
                                String opcion8 = "";

                                do {
                                    opcion8 = JOptionPane.showInputDialog("""
                                                1.Buscar por fecha
                                                2.Buscar por Id
                                                3.Salir
                                              
                                                """);

                                    switch (opcion8){
                                        case "1":
                                            objVueloController.buscarDate();
                                            break;
                                        case "2":
                                            objVueloController.buscarId();

                                            break;
                                    }
                                }while (!opcion8.equals("3"));
                                break;
                            case "4":
                                objVueloController.delete();

                                break;
                            case "5":
                                objVueloController.getAll();

                                break;
                        }
                    }while (!opcion7.equals("6"));
                    break;

                case "4":
                    String opcion4 = "";

                    do {
                        opcion4 = JOptionPane.showInputDialog("""
                                1.Crear Reservacion
                                2.Editar Reservacion
                                3.Buscar Reservacion
                                4.Eliminar Reservacion
                                5.Lista de Reservaciones
                                6.Salir
                                """);

                        switch (opcion4) {
                            case "1":
                                objReservasController.create();

                                break;
                            case "2":
                                objReservasController.edit();

                                break;
                            case "3":
                                String opcion9 = "";

                                do {
                                    opcion9 = JOptionPane.showInputDialog("""
                                                1.Buscar por fecha
                                                2.Buscar por Id
                                                3.Salir
                                              
                                                """);

                                    switch (opcion9){
                                        case "1":
                                            objReservasController.buscarDate();
                                            break;
                                        case "2":
                                            objReservasController.buscarId();

                                            break;
                                    }
                                }while (!opcion9.equals("3"));
                                break;


                            case "4":
                                objReservasController.delete();

                                break;
                            case "5":
                                objReservasController.getAll();

                                break;
                        }
                    }while (!opcion4.equals("6"));
                    break;
            }
        }while (!opcion1.equals("5"));

    }
}