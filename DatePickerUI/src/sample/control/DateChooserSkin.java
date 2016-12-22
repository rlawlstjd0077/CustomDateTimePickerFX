package sample.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateChooserSkin extends SkinBase<DateChooser> {
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMMM yyyy", Locale.ENGLISH);
    private final Date date;
    private final Label month;
    private final BorderPane content;
    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private String currentTime;
    private DateChooser dateChooser;

    private static class CalendarCell extends StackPane {

        private final Date date;
        private Label label;
        public CalendarCell(Date day, String text) {
            this.date = day;
            label = new Label(text);
        }
        public Date getDate() {
            return date;
        }
        public void setLabel(String text){
            label = new Label(text);
        }
        public void addCell(){
            getChildren().add(label);
        }
    }

    public DateChooserSkin(DateChooser dateChooser) {
        super(dateChooser);

        this.dateChooser = dateChooser;
        this.date = dateChooser.getDate();          // this date is the selected date

        // content the part of BorderPane
        HBox topBar = new HBox();
        final DatePickerPane calendarPane = new DatePickerPane(date);               // create center content
        VBox bottomBar = new VBox();

        /* create top content */
        {
            month = new Label(simpleDateFormat.format(calendarPane.getShownMonth()));

            Button monthBack = new Button();
            monthBack.getStyleClass().add("left-button");
            monthBack.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    calendarPane.forward(-1);
                }
            });

            Button monthForward = new Button();
            monthForward.getStyleClass().add("right-button");
            monthForward.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    calendarPane.forward(1);
                }
            });
            topBar.setHgrow(month, Priority.ALWAYS);
            month.setMaxWidth(Double.MAX_VALUE);
            month.setAlignment(Pos.BOTTOM_CENTER);
            topBar.setMargin(month, new Insets(11, 0, 0, 0));
            topBar.setMargin(monthBack, new Insets(7, 0, 0, 0));
            topBar.setMargin(monthForward, new Insets(7, 0, 0, 0));
            topBar.getChildren().addAll(monthBack, month, monthForward);
            topBar.getStyleClass().setAll("top-bar");
            topBar.setPrefHeight(40);
        }

        /*create bottom box content*/
        {
            bottomBar.getStyleClass().setAll("bottom-bar");
            bottomBar.setPrefHeight(124);

            HBox topBox= new HBox();
            HBox bottomBox = new HBox();

            topBox.setPrefHeight(74);
            bottomBox.setPrefHeight(50);


            Label time = new Label("Time : ");
            ComboBox timeCombo = new ComboBox();

            time.setPrefWidth(75);
            time.setPrefHeight(16);
            timeCombo.setPrefWidth(167);
            timeCombo.setPrefHeight(36);

            ObservableList<String> observableList = FXCollections.observableArrayList(getTimeList(dateChooser.getTimeInterval()));
            timeCombo.setItems(observableList);
            this.currentTime = observableList.get(0).substring(0, 13);
            timeCombo.setVisibleRowCount(5);
            timeCombo.setValue(currentTime);
            topBox.setMargin(time, new Insets(30,0,0,20));
            topBox.setMargin(timeCombo, new Insets(24, 0, 0, -10));
            timeCombo.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    currentTime = timeCombo.getValue().toString().substring(0, 13);
                }
            });
            topBox.getChildren().addAll(time, timeCombo);

            Button okButton = new Button("OK");
            Button cancelButton = new Button("CANCEL");
            okButton.setPrefWidth(80);
            cancelButton.setPrefWidth(80);
            bottomBox.setMargin(okButton, new Insets(5,0,0,30));
            bottomBox.setMargin(cancelButton, new Insets(5,0,0,50));

            okButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dateChooser.onChooseDate(currentYear, currentMonth, currentDay, currentTime);
                    System.out.println("ok click");
                }
            });
            cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("cancel click");
                    dateChooser.closeChooser();
                }
            });

            bottomBox.getChildren().addAll(okButton, cancelButton);

            bottomBar.getChildren().addAll(topBox, bottomBox);
        }
        // use a BorderPane to Layout the view
        content = new BorderPane();
        content.setTop(topBar);
        content.setCenter(calendarPane);
        content.setBottom(bottomBar);
        getChildren().add(content);

    }
    public ArrayList<String> getTimeList(int minute){
        ArrayList<String> timeList = new ArrayList<>();
        int divideVal = 1440 / minute;
        for(int i = 0; i < divideVal; i++){
            int time = i * minute;
            timeList.add(String.format("%02d", time / 60 ) + " : " + String.format("%02d", time % 60) + " : "  + "00  " + (time <= 720 ?  "AM" : "PM"));
        }
        return timeList;
    }

    class DatePickerPane extends Region {

        private final Date selectedDate;
        private final Calendar cal;
        private CalendarCell selectedDayCell;
        // this is used to format the day cells
        private final SimpleDateFormat sdf = new SimpleDateFormat("d");
        private int rows, columns;

        public DatePickerPane(Date date) {
            setPrefSize(271, 236);

            this.columns = 7;
            this.rows = 5;

            cal = Calendar.getInstance();
            Date helperDate = new Date(date.getTime());
            cal.setTime(helperDate);

            selectedDate = date;
            refresh();
        }

        public void forward(int i) {
            cal.add(Calendar.MONTH, i);
            month.setText(simpleDateFormat.format(cal.getTime()));
            refresh();
        }

        private void refresh() {
            super.getChildren().clear();
            this.rows = 6;
            currentYear = cal.get(Calendar.YEAR);
            currentMonth = cal.get(Calendar.MONTH);
            currentDay = cal.get(Calendar.DAY_OF_MONTH);
            Date copy = new Date(cal.getTime().getTime());

            // Display a styleable row of localized weekday symbols 
            DateFormatSymbols symbols = new DateFormatSymbols(Locale.ENGLISH);
            String[] dayNames = symbols.getShortWeekdays();

            for (int i = 1; i < 8; i++) { // array starts with an empty field
                CalendarCell calendarCell = new CalendarCell(cal.getTime(), dayNames[i].substring(0, 2));
                calendarCell.addCell();
                calendarCell.getStyleClass().add("weekday-cell");
                super.getChildren().add(calendarCell);
            }

            cal.set(Calendar.DAY_OF_MONTH, 1);
            final int month = cal.get(Calendar.MONTH);

            int weekday = cal.get(Calendar.DAY_OF_WEEK);

            if(weekday  >= Calendar.FRIDAY){
                Calendar check = Calendar.getInstance();
                check.setTime(new Date(cal.getTime().getTime()));
                int lastDate = check.getActualMaximum(Calendar.DATE);
                if((weekday + lastDate) >= 37){
                    rows = 7;
                }
            }

            //The first of DAY_OF_WEEK is Sunday
            cal.set(Calendar.DAY_OF_WEEK, 1);

            // used to identify and style the cell with the selected date;
            Calendar testSelected = Calendar.getInstance();
            testSelected.setTime(selectedDate);

            for (int i = 0; i < (rows); i++) {
                for (int j = 0; j < columns; j++) {
                    String formatted = sdf.format(cal.getTime());
                    final CalendarCell dayCell = new CalendarCell(cal.getTime(), formatted);
                    dayCell.getStyleClass().add("calendar-cell");
                    if (cal.get(Calendar.MONTH) != month) {
                        dayCell.setLabel("");
                        dayCell.addCell();
                        dayCell.getStyleClass().setAll("calendar-cell-inactive");
                    } else {
                        dayCell.addCell();
                        if (isSameDay(testSelected, cal)) {
                            dayCell.getStyleClass().add("calendar-cell-selected");
                            selectedDayCell = dayCell;
                        }
                        if (isToday(cal)) {
                            dayCell.getStyleClass().add("calendar-cell-today");
                        }
                    }

                    dayCell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent arg0) {
                            Calendar currentCal = Calendar.getInstance();
                            currentCal.setTime(dayCell.getDate());
                            currentYear = currentCal.get(Calendar.YEAR);
                            currentMonth = currentCal.get(Calendar.MONTH);
                            currentDay = currentCal.get(Calendar.DAY_OF_MONTH);
                            if (selectedDayCell != null) {
                                selectedDayCell.getStyleClass().add("calendar-cell");
                                selectedDayCell.getStyleClass().remove("calendar-cell-selected");
                            }
                            selectedDate.setTime(dayCell.getDate().getTime());
                            dayCell.getStyleClass().remove("calendar-cell");
                            dayCell.getStyleClass().add("calendar-cell-selected");
                            selectedDayCell = dayCell;
                        }
                    });

                    super.getChildren().add(dayCell);
                    cal.add(Calendar.DATE, 1);
                }
            }
            cal.setTime(copy);
        }

        @Override
        protected ObservableList<Node> getChildren() {
            return FXCollections.unmodifiableObservableList(super.getChildren());
        }

        public Date getShownMonth() {
            return cal.getTime();
        }

        @Override
        protected void layoutChildren() {
            ObservableList<Node> children = getChildren();
            double width = getWidth();
            double height = getHeight();

            double cellWidth = (width / (columns));
            double cellHeight = height / (rows);

            for (int i = 0; i < (rows); i++) {
                for (int j = 0; j < (columns); j++) {
                    if (children.size() <= ((i * (columns)) + j)) {
                        break;
                    }
                    Node get = children.get((i * (columns)) + j);
                    layoutInArea(get, j * cellWidth, i * cellHeight, cellWidth, cellHeight, 0.0d, HPos.LEFT, VPos.TOP);
                }
            }
        }
    }

    private static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
    private static boolean isToday(Calendar cal) {
        return isSameDay(cal, Calendar.getInstance());
    }
}