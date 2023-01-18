package com.example.pendataansiswa;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.*;

public class HelloController {
    @FXML
    TextField tfNISN;
    @FXML
    TextField tfNama;
    @FXML
    TextField tfAlamat;
    @FXML
    Button btnSubmit;
    @FXML
    TableView tvDataSiswa;
    @FXML
    Button btnSave;
    @FXML
    Button btnLoad;

    @FXML
    public void onButtonClicked(ActionEvent e){
        if (e.getTarget().equals(btnSubmit)){
            //Menambahkan data ke TableView saat Button submit ditekan
            ObservableList<dataModel> data = tvDataSiswa.getItems();
            data.add(new dataModel(
                    tfNISN.getText(),
                    tfNama.getText(),
                    tfAlamat.getText()
            ));

            tfNISN.setText("");
            tfNama.setText("");
            tfAlamat.setText("");
        } else if (e.getTarget().equals(btnSave)){
            //Menulis data yang ada di TableView ke file bernama data
            try {
                ObservableList<dataModel> data = tvDataSiswa.getItems();
                BufferedWriter writer = new BufferedWriter(new FileWriter ("data-siswa.dat"));
                for (int i = 0; i < data.size(); i++) {
                    writer.write(String.valueOf(data.get(i).getNisn()) + "," + String.valueOf(data.get(i).getNama())  + "," + String.valueOf(data.get(i).getAlamat()));
                    writer.newLine();
                }
                writer.close();
                //Menampilkan dialog jika berhasil menyimpan file
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Success Save Data to File");
                alert.showAndWait();
            } catch (IOException ex){
                //Menampilkan dialog jika error saat menyimpan file

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("ERROR IO Exception " + ex.getMessage());
                alert.showAndWait();
            }
        } else if (e.getTarget().equals(btnLoad)){
            // Membaca data yang ada di file data-siswa.dat ke TableView

            try {
                ObservableList<dataModel> data = tvDataSiswa.getItems();
                data.clear();
                BufferedReader reader =  new BufferedReader(new FileReader("data-siswa.dat"));
                String line;
                while ((line  = reader.readLine()) != null){
                    String [] temp = line.split(",");
                    dataModel DataModel = new dataModel(temp[0], temp[1], temp[2]);
                    data.add(DataModel);
                }
                reader.close();
                //Menampilkan dialog jika berhasil membaca file
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Success Load Data from Saved File");
                alert.showAndWait();
            } catch (IOException ex){
                //Menampilkan dialog jika error saat membaca file

                Alert alert =  new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Error IOException : " + ex.getMessage());
                alert.showAndWait();
            }
        }
    }

}