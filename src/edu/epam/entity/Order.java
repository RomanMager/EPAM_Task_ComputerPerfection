package edu.epam.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class Order {
    private static final String DELIMITER = "********************************";
    private final int orderNumber;
    // TODO: use generics.
    private final int clientId;
    private Computer computer;
    // TODO: Add local time to order.
    private LocalTime localTime;
    private List<Computer> computers = new ArrayList<>();

    public Order(int clientId, String computerName, int numberOfComputers) {
        this.clientId = clientId;
        IdGenerator idGenerator = new IdGenerator();
        orderNumber = idGenerator.generateOrderId();
        computer = new Computer(computerName, numberOfComputers, orderNumber);
        addComputers(computer, numberOfComputers);
    }

    private void addComputers(Computer computer, int numberOfComputers) {
        for (int i = 0; i < numberOfComputers; i++) {
            computers.add(computer);
        }
    }

    public Computer addComputerCase(ComputerCase computerCase) {
        this.computer.setComputerCase(computerCase);
        return this.computer;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void changeNumberOfOrderedComputers(int newNumber) {
    }

    public void changeComputerName(String computerName) {
    }

    public void showInfo() {
        System.out.println("[" + orderNumber + " : "
                                   + clientId + " : "
                                   + computer.getComputerName() + " : "
                                   + computer.getNumberOfComputers() + " : "
                                   + computer.getComputerPrice()
                                   + "]");
    }

    @Override
    public String toString() {
        try (Formatter formatter = new Formatter()) {
            int finalCost = 0;
            formatter.format("%s%n", DELIMITER);
            formatter.format("%s%d%n", "Заказ: ", getOrderNumber());
            formatter.format("%s%d%n", "Клиент: ", clientId);
            formatter.format("%s", computer.toString());
            finalCost += computer.getComputerPrice() * computer.getNumberOfComputers();
            formatter.format("%-20s%10d%2s%n", "Общая сумма:", finalCost, "$");
            formatter.format("%-20s", DELIMITER);

            CheckOutPrinter checkOutPrinter = new CheckOutPrinter();
            checkOutPrinter.invoiceToFile(formatter, orderNumber);

            return formatter.toString();
        }
    }
}
