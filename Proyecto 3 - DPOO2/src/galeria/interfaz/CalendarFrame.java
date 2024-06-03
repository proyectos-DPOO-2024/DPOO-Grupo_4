package galeria.interfaz;

import javax.swing.*;

import galeria.modelo.centroventas.Fecha;
import galeria.modelo.centroventas.Pago;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Random;
import java.util.List;

public class CalendarFrame extends JFrame {
    private JPanel calendarPanel;
    private JLabel[][] dayLabels;
    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> yearComboBox;
    private JButton updateButton;
    private List<Pago> listaPagos;

    private int currentYear;
    private int currentMonth;

    public CalendarFrame(List<Pago> listaPagos) {
    	
    	this.listaPagos = listaPagos;
    	
        setTitle("Calendario");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for month and year selection
        JPanel topPanel = new JPanel();
        monthComboBox = new JComboBox<>(getMonths());
        yearComboBox = new JComboBox<>(getYears());
        updateButton = new JButton("Actualizar");
        
        topPanel.add(monthComboBox);
        topPanel.add(yearComboBox);
        topPanel.add(updateButton);
        add(topPanel, BorderLayout.NORTH);

        calendarPanel = new JPanel(new GridLayout(7, 7));
        add(calendarPanel, BorderLayout.CENTER);

        dayLabels = new JLabel[7][7];
        
        // Add days of the week headers
        String[] headers = {"Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"};
        for (int i = 0; i < 7; i++) {
            dayLabels[0][i] = new JLabel(headers[i], SwingConstants.CENTER);
            dayLabels[0][i].setOpaque(true);
            dayLabels[0][i].setBackground(Color.LIGHT_GRAY);
            calendarPanel.add(dayLabels[0][i]);
        }
        
        // Initialize day labels
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                dayLabels[i][j] = new JLabel("", SwingConstants.CENTER);
                dayLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                dayLabels[i][j].setOpaque(true);
                calendarPanel.add(dayLabels[i][j]);
            }
        }

        // Set initial year and month
        LocalDate currentDate = LocalDate.now();
        currentYear = currentDate.getYear();
        currentMonth = currentDate.getMonthValue() - 1;
        monthComboBox.setSelectedIndex(currentMonth);
        yearComboBox.setSelectedItem(currentYear);

        // Update calendar when the button is clicked
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMonth = monthComboBox.getSelectedIndex();
                currentYear = (Integer) yearComboBox.getSelectedItem();
                updateCalendar();
            }
        });

        updateCalendar();
    }

    private String[] getMonths() {
        return new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    }

    private Integer[] getYears() {
        Integer[] years = new Integer[100];
        int startYear = 2000;
        for (int i = 0; i < 25; i++) {
            years[i] = startYear + i;
        }
        return years;
    }

    private void updateCalendar() {
        YearMonth currentYearMonth = YearMonth.of(currentYear, currentMonth + 1);

        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue() % 7; // Adjust to make Sunday = 0
        int daysInMonth = currentYearMonth.lengthOfMonth();
        Random random = new Random();

        int dayCounter = 1;
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 1 && j < firstDayOfWeek) {
                    dayLabels[i][j].setText("");
                } else if (dayCounter <= daysInMonth) {
                    int randomNumber = numeroVentas(dayCounter); // Random number between 0 and 99
                    dayLabels[i][j].setText("<html><div style='text-align: right; font-size: smaller;'>" + dayCounter + "</div><div style='text-align: center; font-size: larger;'>Ventas: " + randomNumber + "</div></html>");
                    dayCounter++;
                } else {
                    dayLabels[i][j].setText("");
                }
            }
        }
    }

    private int numeroVentas(int day) {
        int ventas = 0;

        // Iterar sobre la lista de pagos y contar las ventas para el día dado
        for (Pago pago : listaPagos) {
            Fecha fechaPago = pago.getFecha(); // Suponiendo que la clase Pago tiene un método getFecha() que devuelve la fecha del pago
            if (fechaPago != null && fechaPago.getDia() == day) {
                ventas++;
        
            }}
        return ventas;
        }
    
}
