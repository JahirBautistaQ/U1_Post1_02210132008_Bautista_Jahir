package original;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<String[]> orders = new ArrayList<>();

    // Crear pedido
    public void createOrder(String customer, String product,
            double price, int quantity) {
        double total = price * quantity;
        if (total > 1000)
            total *= 0.9; // 10% descuento
        if (total > 5000)
            total *= 0.85; // 15% descuento adicional
        String orderId = "ORD-" + System.currentTimeMillis();
        orders.add(new String[] { orderId, customer, product,
                String.valueOf(total), "PENDING" });
        System.out.println("Orden creada: " + orderId);
        // Guardar en archivo
        try {
            java.io.FileWriter fw = new java.io.FileWriter(
                    "orders.txt", true);
            fw.write(orderId + "," + customer + "," + total + "\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Enviar notificación
        System.out.println("EMAIL a " + customer +
                ": Su pedido " + orderId + " ha sido creado.");
    }

    // Calcular impuestos
    public double calculateTax(String orderId) {
        for (String[] o : orders) {
            if (o[0].equals(orderId)) {
                double total = Double.parseDouble(o[3]);
                return total * 0.19; // IVA 19%
            }
        }
        return 0;
    }

    // Generar reporte
    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE DE PEDIDOS ===\n");
        double grandTotal = 0;
        for (String[] o : orders) {
            sb.append(o[0] + " | " + o[1] + " | $" + o[3] + "\n");
            grandTotal += Double.parseDouble(o[3]);
        }
        sb.append("TOTAL: $" + grandTotal);
        System.out.println(sb.toString());
        return sb.toString();
    }
}
