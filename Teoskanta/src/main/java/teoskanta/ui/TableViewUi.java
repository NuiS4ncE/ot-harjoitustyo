package teoskanta.ui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//import productivetime.domain.Activity;
//import productivetime.domain.ActivityListControl;
import java.util.*;
import teoskanta.domain.Title;
import teoskanta.domain.TitleListService;

public class TableViewUi implements UIElement<TableView<Title>> {

    private TableView<Title> titleTable;
    
    public TableViewUi(TitleListService titleListService){
        titleTable = new TableView<>();
        
        TableColumn<Title, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Title, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        
        TableColumn<Title, String> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        
        titleTable.setItems(titleListService.getObservableTitles());
        titleTable.getColumns().addAll(Arrays.asList(nameColumn, authorColumn, yearColumn));
    }
    
    @Override
    public TableView getLayout() {
        return titleTable;
    }
}