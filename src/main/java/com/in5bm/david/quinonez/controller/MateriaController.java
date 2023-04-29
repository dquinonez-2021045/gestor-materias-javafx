package com.in5bm.david.quinonez.controller;

import com.in5bm.david.quinonez.models.dao.MateriaDaoImpl;
import com.in5bm.david.quinonez.models.domain.Materia;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.Time;
import java.time.Year;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MateriaController implements Initializable {

    private final String PAQUETE_IMAGES = "com/in5bm/david/quinonez/resources/images/";

    @FXML
    private TableColumn<Materia, Integer> colId;
    @FXML
    private TableColumn<Materia, String> colMateria;
    @FXML
    private TableColumn<Materia, Year> colCiclo;
    @FXML
    private TableColumn<Materia, Time> colHorarioI;
    @FXML
    private TableColumn<Materia, Time> colHorarioF;
    @FXML
    private TableColumn<Materia, String> colCaatedratico;
    @FXML
    private TableColumn<Materia, String> colSalon;
    @FXML
    private TableColumn<Materia, Integer> colCupoMax;
    @FXML
    private TableColumn<Materia, Integer> colCupoMin;
    @FXML
    private TableColumn<Materia, Integer> colNota;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombreM;
    @FXML
    private TextField txtSalon;
    @FXML
    private Spinner<Integer> spCiclo;
    @FXML
    private Spinner<Integer> spCupoMax;
    @FXML
    private Spinner<Integer> spCupoMin;
    @FXML
    private Spinner<Integer> spNota;
    @FXML
    private JFXTimePicker tmHInicio;
    @FXML
    private JFXTimePicker tmHFinal;
    @FXML
    private TextField txtCatedratico;
    @FXML
    private TableView<Materia> tblMateria;

    /**
     * Initializes the controller class.
     */
    private SpinnerValueFactory<Integer> valueFactoryCiclo;
    private SpinnerValueFactory<Integer> valueFactoryCupoMaximo;
    private SpinnerValueFactory<Integer> valueFactoryCupoMinimo;
    private SpinnerValueFactory<Integer> valueFactoryNota;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private ImageView imgNuevo;
    @FXML
    private ImageView imgModificar;
    @FXML
    private Label lbCont;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        valueFactoryCiclo = new SpinnerValueFactory.IntegerSpinnerValueFactory(2021, 2050, 2022);
        spCiclo.setValueFactory(valueFactoryCiclo);

        valueFactoryCupoMaximo = new SpinnerValueFactory.IntegerSpinnerValueFactory(23, 65, 24);
        spCupoMax.setValueFactory(valueFactoryCupoMaximo);

        valueFactoryCupoMinimo = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 22, 5);
        spCupoMin.setValueFactory(valueFactoryCupoMinimo);

        valueFactoryNota = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 60);
        spNota.setValueFactory(valueFactoryNota);

        conteo();
        cargarDatos();
    }

    MateriaDaoImpl materias = new MateriaDaoImpl();

    private void deshabilitarCampos() {
        txtId.setEditable(false);
        txtId.setDisable(true);

        txtNombreM.setEditable(false);
        txtNombreM.setDisable(true);

        spCiclo.setEditable(false);
        spCiclo.setDisable(true);

        txtCatedratico.setEditable(false);
        txtCatedratico.setDisable(true);

        txtSalon.setEditable(false);
        txtSalon.setDisable(true);

        spCupoMax.setEditable(false);
        spCupoMax.setDisable(true);

        spCupoMin.setEditable(false);
        spCupoMin.setDisable(true);

        spNota.setEditable(false);
        spNota.setDisable(true);

    }

    private void habilitarCampos() {
        txtId.setEditable(false);
        txtId.setDisable(true);
        txtNombreM.setEditable(true);
        txtNombreM.setDisable(false);
        spCiclo.setEditable(true);
        spCiclo.setDisable(false);

        txtCatedratico.setEditable(true);
        txtCatedratico.setDisable(false);

        txtSalon.setEditable(true);
        txtSalon.setDisable(false);

        spCupoMax.setEditable(true);
        spCupoMax.setDisable(false);

        spCupoMin.setEditable(true);
        spCupoMin.setDisable(false);

        spNota.setEditable(true);
        spNota.setDisable(false);
    }

    public void cargarDatos() {
        tblMateria.setItems(materias.getAll());
        colId.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("idMateria"));
        colMateria.setCellValueFactory(new PropertyValueFactory<Materia, String>("nombreMateria"));
        colCiclo.setCellValueFactory(new PropertyValueFactory<>("cicloEscolar"));
        colHorarioI.setCellValueFactory(new PropertyValueFactory<Materia, Time>("horarioInicio"));
        colHorarioF.setCellValueFactory(new PropertyValueFactory<Materia, Time>("horarioFinal"));
        colCaatedratico.setCellValueFactory(new PropertyValueFactory<Materia, String>("catedratico"));
        colSalon.setCellValueFactory(new PropertyValueFactory<Materia, String>("salon"));
        colCupoMax.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("cupoMaximo"));
        colCupoMin.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("cupoMinimo"));
        colNota.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("nota"));
        conteo();
        numero = materias.getAll().size();
        System.out.println("EL NUMERO DE DATOS ES DE " + numero);
    }

    public boolean existeElemento() {
        if ((tblMateria.getSelectionModel().getSelectedItem() != null)) {
            return true;
        } else if ((tblMateria.getSelectionModel().getSelectedItem() == null)) {
            return false;
        }
        return false;
    }

    @FXML
    public void seleccionarElemento() {
        if (existeElemento()) {
            txtId.setText(String.valueOf((((Materia) tblMateria.getSelectionModel().getSelectedItem()).getIdMateria())));
            txtNombreM.setText(((Materia) tblMateria.getSelectionModel().getSelectedItem()).getNombreMateria());
            spCiclo.getValueFactory().setValue(((Materia) tblMateria.getSelectionModel().getSelectedItem()).getCicloEscolar());
            tmHInicio.setValue(((Materia) tblMateria.getSelectionModel().getSelectedItem()).getHorarioInicio());
            tmHFinal.setValue(((Materia) tblMateria.getSelectionModel().getSelectedItem()).getHorarioFinal());
            txtCatedratico.setText(((Materia) tblMateria.getSelectionModel().getSelectedItem()).getCatedratico());
            txtSalon.setText(((Materia) tblMateria.getSelectionModel().getSelectedItem()).getSalon());
            spCupoMax.getValueFactory().setValue(((Materia) tblMateria.getSelectionModel().getSelectedItem()).getCupoMaximo());
            spCupoMin.getValueFactory().setValue(((Materia) tblMateria.getSelectionModel().getSelectedItem()).getCupoMinimo());
            spNota.getValueFactory().setValue(((Materia) tblMateria.getSelectionModel().getSelectedItem()).getNota());
        }
    }

    private void limpiarCampos() {
        txtId.clear();
        txtCatedratico.clear();
        txtNombreM.clear();
        txtSalon.clear();
        spCiclo.getValueFactory().setValue(0);
        spCupoMax.getValueFactory().setValue(0);
        spCupoMin.getValueFactory().setValue(0);
        spNota.getValueFactory().setValue(0);
        tmHFinal.getEditor().clear();
        tmHInicio.getEditor().clear();
    }

    private enum Operacion {
        NINGUNO, GUARDAR, ACTUALIZAR
    }

    private Operacion operacion = Operacion.NINGUNO;

    @FXML
    private void clicAgregar(ActionEvent event) {
        switch (operacion) {
            case NINGUNO: //Primer Clic Nuevo
                habilitarCampos();
                tblMateria.getSelectionModel().clearSelection();
                tblMateria.setDisable(true);
                limpiarCampos();
                btnAgregar.setText("Guardar");
                //  imgNuevo.setImage(new Image(PAQUETE_IMAGES + "save.png"));
                btnModificar.setText("Cancelar");
                // imgModificar.setImage(new Image(PAQUETE_IMAGES + "close.png"));
                btnEliminar.setDisable(true);
                btnEliminar.setVisible(true);
                operacion = Operacion.GUARDAR;
                break;

            case GUARDAR:

                if (txtNombreM.getText().isEmpty()) {
                    validacionI();
                } else if (txtCatedratico.getText().isEmpty()) {
                    validacionI();
                } else if (txtSalon.getText().isEmpty()) {
                    validacionI();
                } else if (txtNombreM.getText().length() > 45) {
                    validacionI();
                } else if (txtCatedratico.getText().length() > 45) {
                    validacionI();
                } else if (txtSalon.getText().length() > 45) {
                    validacionI();
                } else if (tmHFinal.getValue() == null) {
                    validacionI();
                } else if (tmHInicio.getValue() == null) {
                    validacionI();
                } else if (agregarMateria()) {
                    cargarDatos();
                    conteo();
                    limpiarCampos();
                    deshabilitarCampos();
                    tblMateria.getSelectionModel().clearSelection();
                    btnAgregar.setText("Nuevo");
                    //   imgNuevo.setImage(new Image(PAQUETE_IMAGES + "bloc-de-dibujo.png"));
                    btnModificar.setText("Modificar");
                    // imgModificar.setImage(new Image(PAQUETE_IMAGES + "editar-codigo(1).png"));
                    btnEliminar.setDisable(false);
                    btnEliminar.setVisible(true);
                    tblMateria.setDisable(false);
                    operacion = Operacion.NINGUNO;

                }
                break;
        }
    }

    @FXML
    private void clicModificar(ActionEvent event) {
        switch (operacion) {
            case NINGUNO:
                if (existeElemento()) {
                    habilitarCampos();
                    /*tblAlumnos.setDisable(false);*/

                    btnAgregar.setDisable(true);
                    btnAgregar.setVisible(false);

                    btnModificar.setText("Guardar");
                    //  imgModificar.setImage(new Image(PAQUETE_IMAGES + "disco-flexible.png"));

                    btnEliminar.setText("Cancelar");
                    //imgEliminar.setImage(new Image(PAQUETE_IMAGES + "icons8-cancelar-100.png"));

                    operacion = Operacion.ACTUALIZAR;
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Materias");
                    alert.setHeaderText(null);
                    alert.setContentText("Antes de continuar selecciona un registro");
                    Stage stagee = (Stage) alert.getDialogPane().getScene().getWindow();
                    alert.show();
                }
                break;
            case GUARDAR: //Cancelar

                btnAgregar.setText("Nuevo");
                // imgNuevo.setImage(new Image(PAQUETE_IMAGES + "pagina.png"));

                btnModificar.setText("Modificar");
                // imgModificar.setImage(new Image(PAQUETE_IMAGES + "editar.png"));

                btnEliminar.setDisable(false);
                btnEliminar.setVisible(true);

                limpiarCampos();
                deshabilitarCampos();

                tblMateria.setDisable(false);

                operacion = Operacion.NINGUNO;
                break;
            case ACTUALIZAR:
                if (existeElemento()) {
                    if (txtNombreM.getText().isEmpty()) {
                        validacionI();
                    } else if (txtCatedratico.getText().isEmpty()) {
                        validacionI();
                    } else if (txtSalon.getText().isEmpty()) {
                        validacionI();
                    } else if (txtNombreM.getText().length() > 45) {
                        validacionI();
                    } else if (txtCatedratico.getText().length() > 45) {
                        validacionI();
                    } else if (txtSalon.getText().length() > 45) {
                        validacionI();
                    } else if (tmHFinal.getValue() == null) {
                        validacionI();
                    } else if (tmHInicio.getValue() == null) {
                        validacionI();
                    } else if (actualizarMateria()) {
                        limpiarCampos();
                        cargarDatos();
                        deshabilitarCampos();

                        tblMateria.setDisable(false);

                        tblMateria.getSelectionModel().clearSelection();

                        btnModificar.setText("Nuevo");
                        btnAgregar.setDisable(false);
                        btnAgregar.setVisible(true);
                        // imgModificar.setImage(new Image(PAQUETE_IMAGES + "pagina.png"));

                        btnModificar.setText("Modificar");
                        //  imgModificar.setImage(new Image(PAQUETE_IMAGES + "editar.png"));

                        btnEliminar.setText("Eliminar");
                        // imgEliminar.setImage(new Image(PAQUETE_IMAGES + "eliminar.png"));

                        operacion = Operacion.NINGUNO;

                    }
                }

                break;
        }
    }

    @FXML
    private void clicEliminar(ActionEvent event) {
        switch (operacion) {
            case ACTUALIZAR: //Cancelar la actualizacion
                btnAgregar.setDisable(false);
                btnAgregar.setVisible(true);

                btnModificar.setText("Modificar");
                // imgModificar.setImage(new Image(PAQUETE_IMAGES + "editar.png"));

                btnEliminar.setText("Eliminar");
                //imgEliminar.setImage(new Image(PAQUETE_IMAGES + "eliminar.png"));

                limpiarCampos();
                deshabilitarCampos();

                tblMateria.getSelectionModel().clearSelection();

                operacion = Operacion.NINGUNO;
                break;
            case NINGUNO:
                if (existeElemento()) {
                    Alert alertaC = new Alert(Alert.AlertType.CONFIRMATION);
                    alertaC.setTitle("Materias");
                    alertaC.setHeaderText(null);
                    alertaC.setContentText("¿Seguro que quieres eliminar el registro?");
                    Stage dialog = new Stage();
                    Stage stage = (Stage) alertaC.getDialogPane().getScene().getWindow();

                    Optional<ButtonType> botonC = alertaC.showAndWait();

                    if (botonC.get().equals(ButtonType.OK)) {

                        if (eliminarMateria()) {

                            //lista.remove(tblMateria.getSelectionModel().getFocusedIndex());
                            conteo();
                            limpiarCampos();
                            cargarDatos();

                            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                            alerta.setTitle("Materias");
                            alerta.setHeaderText(null);
                            alerta.setContentText("Eliminacion Exitosa");
                            Stage stag = (Stage) alerta.getDialogPane().getScene().getWindow();

                            alerta.show();

                            limpiarCampos();
                        }
                    } else if (botonC.get().equals(ButtonType.CANCEL)) {
                        alertaC.close();
                        tblMateria.getSelectionModel().clearSelection();
                        limpiarCampos();
                    }
                } else {

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Materias");
                    alert.setHeaderText(null);
                    alert.setContentText("Antes de continuar selecciona un registro");
                    Stage dialog = new Stage();
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

                    alert.show();

                }
                break;
        }
    }

    private boolean eliminarMateria() {
        Materia materia = (Materia) tblMateria.getSelectionModel().getSelectedItem();
        materias.delete(materia);
        cargarDatos();
        limpiarCampos();
        conteo();
        return true;
    }

    public boolean agregarMateria() {
        Materia mat = new Materia();
        mat.setNombreMateria(txtNombreM.getText());
        mat.setCicloEscolar(spCiclo.getValue());
        mat.setHorarioInicio(tmHInicio.getValue());
        mat.setHorarioFinal(tmHFinal.getValue());
        mat.setCatedratico(txtCatedratico.getText());
        mat.setSalon(txtSalon.getText());
        mat.setCupoMaximo(spCupoMax.getValue());
        mat.setCupoMinimo(spCupoMin.getValue());
        mat.setNota(spNota.getValue());
        
        conteo();
        mat = new Materia(
                mat.getNombreMateria(),
                mat.getCicloEscolar(),
                mat.getHorarioInicio(),
                mat.getHorarioFinal(),
                mat.getCatedratico(),
                mat.getSalon(),
                mat.getCupoMaximo(),
                mat.getCupoMinimo(),
                mat.getNota()
        );

        materias.add(mat);

        cargarDatos();
        conteo();
        limpiarCampos();
        return true;
    }

    public boolean actualizarMateria() {
        Materia mat = new Materia();

        mat.setIdMateria(Integer.valueOf(txtId.getText()));
        mat.setNombreMateria(txtNombreM.getText());
        mat.setCicloEscolar(spCiclo.getValue());
        mat.setHorarioInicio(tmHInicio.getValue());
        mat.setHorarioFinal(tmHFinal.getValue());
        mat.setCatedratico(txtCatedratico.getText());
        mat.setSalon(txtSalon.getText());
        mat.setCupoMaximo(spCupoMax.getValue());
        mat.setCupoMinimo(spCupoMin.getValue());
        mat.setNota(spNota.getValue());

        mat = new Materia(
                mat.getIdMateria(),
                mat.getNombreMateria(),
                mat.getCicloEscolar(),
                mat.getHorarioInicio(),
                mat.getHorarioFinal(),
                mat.getCatedratico(),
                mat.getSalon(),
                mat.getCupoMaximo(),
                mat.getCupoMinimo(),
                mat.getNota()
        );
        materias.update(mat);
        cargarDatos();
        limpiarCampos();
        return true;
    }

    private void validacionI() {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Materias");
        alerta.setHeaderText(null);
        Stage stagee = (Stage) alerta.getDialogPane().getScene().getWindow();
        alerta.show();

        if (txtNombreM.getText().isEmpty()) {
            alerta.setContentText("Ingrese el nombre de la materia");
        } else if (txtCatedratico.getText().isEmpty()) {
            alerta.setContentText("Ingrese el nombre del catedratico");
        } else if (txtSalon.getText().isEmpty()) {
            alerta.setContentText("Ingrese el salon");
        } else if (txtNombreM.getText().length() > 45) {
            alerta.setContentText("El nombre de la materia se pasa del valor establecido |45|");
        } else if (txtCatedratico.getText().length() > 45) {
            alerta.setContentText("El nombre del catedratico se pasa del valor establecido |45|");
        } else if (txtSalon.getText().length() > 45) {
            alerta.setContentText("El salon se pasa del valor establecido |45|");
        } else if (tmHFinal.getValue() == null) {
            alerta.setContentText("Ingrese el horario final");
        } else if (tmHInicio.getValue() == null) {
            alerta.setContentText("Ingrese el horario inicio");
        }
    }
    int numero;

    private void conteo() {
        lbCont.setText("Datos totales: " + "| " + materias.getAll().size() + " |");
        System.out.println("El conteo es de " + materias);
    }

   
}
