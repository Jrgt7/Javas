import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class Factura {
    int numero; String cliente; double valor;

    public Factura(int numero, String cliente, double valor) {
        this.numero = numero; this.cliente = cliente; this.valor = valor;
    }

 @Override
    public String toString() {
        return "Factura #" + numero + " | Cliente: " + cliente + " | Valor: $" + valor;
    }
}

public class SistemaFacturas extends JFrame {

    private ArrayList<Factura> listaFacturas = new ArrayList<>();

    public SistemaFacturas() {
        setTitle("Sistem de Facturación");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JButton btnRegistro = new JButton("1. Registrar ");
        JButton btnConsulta = new JButton("2. Consulta de Factura");
        JButton btnMostrarArchivo = new JButton("3. Mostrar y Guardar en Archivo");
        JButton btnSalir = new JButton("Salir");

        add(btnRegistro);
        add(btnConsulta);
        add(btnMostrarArchivo);
        add(btnSalir);

        // Acción  registrar factura
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String numStr = JOptionPane.showInputDialog("Ingrese número de factura:");
                    int numero = Integer.parseInt(numStr);
                    String cliente = JOptionPane.showInputDialog("Ingrese nombre del cliente:");
                    String valorStr = JOptionPane.showInputDialog("Ingrese valor:");
                    double valor = Double.parseDouble(valorStr);

                    Factura f = new Factura(numero, cliente, valor);
                    listaFacturas.add(f);

                    JOptionPane.showMessageDialog(null, "Factura registrada .");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error .");
                }
            }
        });

        // Accion consultar factura
        btnConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numStr = JOptionPane.showInputDialog("Ingrese número de factura a consultar:");
                int numero = Integer.parseInt(numStr);

                Factura encontrada = null;
                for (Factura f : listaFacturas) {
                    if (f.numero == numero) {
                        encontrada = f;
                        break;
                    }
                }

                if (encontrada != null) {
                    JOptionPane.showMessageDialog(null, encontrada.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Factura no se encuentra registrada.");
                }
            }
        });

        // Guardar y mostrar en archivo
        btnMostrarArchivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter fw = new FileWriter("facturas.txt");
                    BufferedWriter bw = new BufferedWriter(fw);

                    StringBuilder sb = new StringBuilder();
                    for (Factura f : listaFacturas) {
                        sb.append(f.toString()).append("\n");
                        bw.write(f.toString());
                        bw.newLine();
                    }

                    bw.close();
                    JOptionPane.showMessageDialog(null, "Facturas guardadas  facturas.txt\n\n" + sb.toString());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error  guardar en archivo.");
                }
            }
        });

        // Exit
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new SistemaFacturas();
    }
}

    
