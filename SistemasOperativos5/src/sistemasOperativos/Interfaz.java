/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemasOperativos;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author javier
 */
public class Interfaz extends javax.swing.JFrame {
    int numeroProcesos=0;
    int contadorGlobal=0;
    int id=0;
    int procesosListos=0;
    String idAux="";
    int quantum=0;
    
    
    boolean keyP = false, keyE = false,
             keyC = false, keyI = false, 
            keyT = false, keyN = false;
    
    
    private final ArrayList<Procesos> procesos= new ArrayList<>();
    private final ArrayList<Procesos> listos= new ArrayList<>();
    private final ArrayList<Procesos> bloqueados= new ArrayList<>();
    private final ArrayList<Procesos> terminados= new ArrayList<>();
    
    DefaultTableModel modelo = new DefaultTableModel();
    
    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        mostrarDatos();
        
        tablaDatos.setVisible(true);
    }
    
     public void meterDatosAlaTabla() {
        //espera=servicio-restante

        int esperaAux;
        String llegada;

        /*String id,String op,String res,String ttl,String tf,
         String ret,String ser,String esp,String resp, String estado, String rest*/
        for (int i = 0; i < listos.size(); i++) {
            datosEnTabla(listos.get(i).getId(),
                    "",
                    "", "",
                    "", "", "", "",
                    "", "nuevos", "");
        }

        for (int i = 0; i < procesos.size(); i++) {

            if (procesos.get(i).getId().equals(idAux)) {

                if (procesos.get(i).getTiempoEspera().equals("")) {
                    //procesos.get(i).setTiempoEspera("0");
                    llegada = procesos.get(i).getTiempoLlegada();
                    esperaAux = contadorGlobal - Integer.parseInt(llegada);
                    procesos.get(i).setTiempoEspera(Integer.toString(esperaAux));
                }

                datosEnTabla(procesos.get(i).getId(),
                        procesos.get(i).getOp1() + procesos.get(i).getOperators() + procesos.get(i).getOp2(),
                        "", procesos.get(i).getTiempoLlegada(),
                        "", "", procesos.get(i).getTiempoTranscurrido(), procesos.get(i).getTiempoEspera(),
                        procesos.get(i).getTiempoRespuesta(), "ejecucion", procesos.get(i).getTiempoMaximo());
            } else {

                llegada = procesos.get(i).getTiempoLlegada();
                esperaAux = contadorGlobal - Integer.parseInt(llegada);
                procesos.get(i).setTiempoEspera(Integer.toString(esperaAux));

                datosEnTabla(procesos.get(i).getId(),
                        procesos.get(i).getOp1() + procesos.get(i).getOperators() + procesos.get(i).getOp2(),
                        "", procesos.get(i).getTiempoLlegada(),
                        "", "", procesos.get(i).getTiempoTranscurrido(), procesos.get(i).getTiempoEspera(),
                        procesos.get(i).getTiempoRespuesta(), "listos", procesos.get(i).getTiempoMaximo());
            }

        }
        for (int i = 0; i < bloqueados.size(); i++) {

            llegada = bloqueados.get(i).getTiempoLlegada();
            esperaAux = contadorGlobal - Integer.parseInt(llegada);
            bloqueados.get(i).setTiempoEspera(Integer.toString(esperaAux));

            datosEnTabla(bloqueados.get(i).getId(),
                    bloqueados.get(i).getOp1() + "+" + bloqueados.get(i).getOp2(),
                    "", bloqueados.get(i).getTiempoLlegada(),
                    "", "", bloqueados.get(i).getTiempoTranscurrido(), bloqueados.get(i).getTiempoEspera(), // espera
                    bloqueados.get(i).getTiempoRespuesta(), "bloqueados", bloqueados.get(i).getTiempoMaximo());

        }
        for (int i = 0; i < terminados.size(); i++) {
            
            if(terminados.get(i).getResult().equals("E")){
                datosEnTabla(terminados.get(i).getId(),
                    terminados.get(i).getOp1()+" "+
                    terminados.get(i).getOperators()+" "+
                    terminados.get(i).getOp2()
                    ,
                    terminados.get(i).getResult(),
                    terminados.get(i).getTiempoLlegada(),
                    terminados.get(i).getTiempoFinalizacion(),
                    terminados.get(i).getTiempoRetorno(),
                    terminados.get(i).getTiempoServicio(),
                    terminados.get(i).getTiempoEspera(), // espera
                    terminados.get(i).getTiempoRespuesta(),
                    "Error",
                    terminados.get(i).getTiempoRestante());
            }
            else{
                datosEnTabla(terminados.get(i).getId(),
                    terminados.get(i).getOp1()+" "+
                    terminados.get(i).getOperators()+" "+
                    terminados.get(i).getOp2()
                    ,
                    terminados.get(i).getResult(),
                    terminados.get(i).getTiempoLlegada(),
                    terminados.get(i).getTiempoFinalizacion(),
                    terminados.get(i).getTiempoRetorno(),
                    terminados.get(i).getTiempoServicio(),
                    terminados.get(i).getTiempoEspera(), // espera
                    terminados.get(i).getTiempoRespuesta(),
                    "Normal",
                    terminados.get(i).getTiempoRestante());
            }
            
            
            
            

        }
    }
    
    
    
        public void mostrarDatos() {
        modelo.addColumn("Id");
        modelo.addColumn("Estado");
        modelo.addColumn("Op");
        modelo.addColumn("Resultado");
        modelo.addColumn("TLL");
        modelo.addColumn("TF");
        modelo.addColumn("TRetorno");
        modelo.addColumn("TServicio");
        modelo.addColumn("TEspera");
        modelo.addColumn("TRespuesta");
        modelo.addColumn("TRestante");
        tablaDatos.setModel(modelo);
    }
        
        public void datosEnTabla(String id, String op, String res, String ttl, String tf,
            String ret, String ser, String esp, String resp, String estado, String rest) {
        String[] datos = new String[11];

        datos[0] = id;
        datos[1] = estado;
        datos[2] = op;
        datos[3] = res;
        datos[4] = ttl;
        datos[5] = tf;
        datos[6] = ret;
        datos[7] = ser;
        datos[8] = esp;
        datos[9] = resp;
        datos[10] = rest;

        modelo.addRow(datos);
        tablaDatos.setModel(modelo);

    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tNumeroProcesos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tQuantum = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lProcesosEspera = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lContadorGlobal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lContadorBloqueados = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tProcesosListos = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tProcesoEjecucion = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ltiempoTranscurrido = new javax.swing.JLabel();
        lTiempoRestante = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tBloqueados = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tTerminados = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        lQuantum = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("NumeroProcesos: ");

        jLabel2.setText("Quantum: ");

        jButton1.setText("Ejecutar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jButton2.setText("Registrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Procesos en espera: ");

        lProcesosEspera.setText("0");

        jLabel4.setText("Contador Global:");

        lContadorGlobal.setText("0");

        jLabel5.setText("Contador bloqueados:");

        lContadorBloqueados.setText("0");

        jLabel6.setText("Procesos listos.");

        tProcesosListos.setColumns(20);
        tProcesosListos.setRows(5);
        jScrollPane1.setViewportView(tProcesosListos);

        jLabel7.setText("Proceso en ejecucion.");

        tProcesoEjecucion.setColumns(20);
        tProcesoEjecucion.setRows(5);
        jScrollPane2.setViewportView(tProcesoEjecucion);

        jLabel8.setText("Tiempo transcurrido:");

        jLabel9.setText("Tiempo restante:");

        ltiempoTranscurrido.setText("0");

        lTiempoRestante.setText("0");

        jLabel10.setText("Bloqueados");

        tBloqueados.setColumns(20);
        tBloqueados.setRows(5);
        jScrollPane3.setViewportView(tBloqueados);

        jLabel11.setText("Procesos terminados.");

        tTerminados.setColumns(20);
        tTerminados.setRows(5);
        jScrollPane4.setViewportView(tTerminados);

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tablaDatos);

        jLabel12.setText("Quantum:");

        lQuantum.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                                .addGap(649, 649, 649))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(49, 49, 49)
                                        .addComponent(jButton2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tNumeroProcesos, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(6, 6, 6)
                                                .addComponent(lProcesosEspera))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(6, 6, 6)
                                                .addComponent(lContadorBloqueados)))
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lContadorGlobal))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lQuantum)))))
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(107, 107, 107)
                                        .addComponent(jLabel7))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(105, 105, 105)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane2)
                                            .addComponent(jScrollPane3)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ltiempoTranscurrido))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lTiempoRestante))
                                            .addComponent(jLabel10)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(ltiempoTranscurrido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lTiempoRestante))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(tNumeroProcesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(lProcesosEspera)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(lContadorGlobal)))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lContadorBloqueados)
                                .addComponent(jLabel12)
                                .addComponent(lQuantum)))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        
        
        int op1, op2, op, tiempoMaximo;
        String[] operadores = {"+", "/", "*", "%", "+", "sqrt", "-"};
        
        numeroProcesos= Integer.parseInt(tNumeroProcesos.getText());
        quantum=Integer.parseInt(tQuantum.getText());
        
        lProcesosEspera.setText(Integer.toString(numeroProcesos));
        
        tQuantum.setText("");
        tNumeroProcesos.setText("");
        
        for(int i=1; i<=numeroProcesos; i++){
            op1 = (int) (Math.random() * 25 + 1);
            op = (int) (Math.random() * 2 + 0);
            op2 = (int) (Math.random() * 25 + 1);
            tiempoMaximo = (int) (Math.random() * 15 + 5);
            
           if (i >= 5) {

                listos.add(new Procesos(
                        Integer.toString(id),
                        "",Integer.toString(op1),operadores[op],
                        Integer.toString(op2),"", //operaciones
                        Integer.toString(tiempoMaximo),"",
                        "","","","","","",Integer.toString(contadorGlobal),
                        Integer.toString(tiempoMaximo),
                        Integer.toString(quantum),"0" //tiempos
                
                ));
               

            }
           else{
               
               procesos.add(new Procesos(
                        Integer.toString(id),
                        "",Integer.toString(op1),operadores[op],
                        Integer.toString(op2),"", //operaciones
                        Integer.toString(tiempoMaximo),"",
                        "","","","","","",Integer.toString(contadorGlobal),
                       Integer.toString(tiempoMaximo),
                       Integer.toString(quantum),"0" //tiempos
               
               ));
               
               
           }
           
           
           
           id++;
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        
        procesosListos=numeroProcesos;
        if(procesosListos < 4){
            procesosListos=0;
        }
        else{
            procesosListos-=4;
        }
        
        lProcesosEspera.setText(Integer.toString(procesosListos));
        
        mostrarProcesos();
        
       try {
            simularContador();
            // task.isDone();
        } catch (InterruptedException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
         switch (evt.getKeyCode()) {
            case KeyEvent.VK_E:
                if (!keyP || !keyT) {
                    keyE = true;
                }

                break;
            case KeyEvent.VK_P:
                keyP = true;
                break;
            case KeyEvent.VK_C:
                keyC = true;
                tablaDatos.setVisible(false);
                modelo.getDataVector().removeAllElements();
                synchronized (this) {
                    notifyAll();
                }
                break;
            case KeyEvent.VK_I:

                if (!keyP || !keyT) {
                    keyI = true;
                }

                break;
            case KeyEvent.VK_T:
                keyT = true;

                break;
            case KeyEvent.VK_N:

                if (!keyP || !keyT) {

                    keyN = true;

                }

                break;

        }

        
        
        
    }//GEN-LAST:event_jButton1KeyPressed

        public void simularContador() throws InterruptedException {
        Executors.newSingleThreadExecutor().execute(new Runnable() {

            @Override
            public void run() {
                try {
                    proceso();
                    //To change body of generated methods, choose Tools | Templates.
                } catch (InterruptedException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }
        
 public void proceso() throws InterruptedException{
    
     int tiempoRespuesta=1;
     String op;
     int tiempo,tiempoBloqueados=1;
     boolean iniciaContadorBloqueado=false;
     boolean round=false;
     
     
     while(numeroProcesos!=0){
     System.out.println("Numero de procesos "+numeroProcesos);
     while(!procesos.isEmpty()){
         
          Procesos p= (Procesos)procesos.get(0);
         
         
         idAux=p.getId();
         calcularTiempoRespuesta(tiempoRespuesta);
         
         mostrarBloqueados();
         mostrarProcesos();
         
         tProcesoEjecucion.setText(
             "Operacion: " + p.getOp1() + " " + p.getOperators() + " " + p.getOp2() + "\n"
                        + "Tiempo maximo estimado: " + p.getTiempoMaximo() + "\n"
                        + "id: " + p.getId()+"\n"
                        +"Quantum: "+p.getQuantum()+"\n"
         );
         
         p.setResult(operacion(0));
         op=p.getOp1()+" "+p.getOperators()+" "+p.getOp2();
         
         tiempo= Integer.parseInt(p.getTiempoRestante());
         
         for(int i=0; i<tiempo; i++){
             
             tiempoRespuesta++;
             ltiempoTranscurrido.setText(Integer.toString(i));
             lTiempoRestante.setText(Integer.toString(tiempo-i));
             lContadorGlobal.setText(Integer.toString(contadorGlobal));
             mostrarProcesos();
             
             p.setTiempoTranscurrido(Integer.toString(i));
             p.setTiempoRestante(Integer.toString(tiempo-i));
             
             Thread.sleep(500);
             
             
             
             if(iniciaContadorBloqueado){
                 tiempoBloqueados++;
             }
             if(tiempoBloqueados==9 || tiempoBloqueados>9){
                 
                 tiempoBloqueados=1;
                 bloqueadoListo();
                 mostrarBloqueados();
             }
             
             lQuantum.setText(Integer.toString(i));
             if(p.getQuantum().equals(Integer.toString(i))){
                 lQuantum.setText(Integer.toString(i));
                 round=true;
                 carrusel();
                 mostrarProcesos();
                 break;
                 
             }
             
             
             
             lContadorBloqueados.setText(Integer.toString(tiempoBloqueados));
             
             /*
             Bloque Key
             */
             
             if(keyP){
                 
               synchronized (this){
                   wait();
               }
               keyP=false;
                 
             }
             if(keyT){
                 //Tabla
                 meterDatosAlaTabla();
                 tablaDatos.setVisible(true);
                synchronized (this){
                   wait();
               }
               keyT=false;
                 
             }
             if(keyE){
                 p.setResult("E");
                 p.setTiempoTranscurrido(Integer.toString(i));
                 op="X";
                 break;
             }
             if(keyI){
                 if(bloqueados.isEmpty())tiempoBloqueados=1;
                 
                 
                 bloqueados.add(new Procesos(
                    p.getId(),p.getNombre(),p.getOp1(),p.getOperators(),
                    p.getOp2(),p.getResult(),p.getTiempoMaximo(),p.getLote(),
                    p.getTiempoTranscurrido(),p.getTiempoFinalizacion(),
                    p.getTiempoEspera(),p.getTiempoServicio(),p.getTiempoRetorno(),
                    p.getTiempoRespuesta(),p.getTiempoLlegada(),
                    p.getTiempoRestante(),p.getQuantum(),p.getTiempoBloqueado()
                ));
                 
                 
                procesos.remove(0);
                
                iniciaContadorBloqueado=true;
                break;
                 
                 
                 
             }
             if(keyN){
                 registrarNuevo();
                 mostrarProcesos();
                 keyN=false;
                 
             }
             /*
             Fin
             */
             
             contadorGlobal++;
             
         }
         
         if(round){
             round=false;
             break;
         }
         
         if(keyI){
             keyI=false;
             break;
         }
         
         if(keyE){
             keyE=false;
             p.setTiempoServicio(p.getTiempoTranscurrido());
             
             
             
         }
         else{
             p.setTiempoServicio(p.getTiempoMaximo());
             
             
             
         }
         
         
         
         
         p.setTiempoFinalizacion(Integer.toString(contadorGlobal));
         tiempoRetorno();//Calcular tiempo retorno
         tiempoEspera();
         
         tTerminados.append(
             "id: " + p.getId() + "\t"
            + "Op: " + op + "\t"
            + "resultado: " + p.getResult() + "\t"
            + "TLL: " + p.getTiempoLlegada() + "\t"
            + "TF: " + p.getTiempoFinalizacion() + "\t"
            + "TRetorno: " + p.getTiempoRetorno() + "\t"
            + "TServicio: " + p.getTiempoServicio()+ "\t"
            + "TEspera: " + p.getTiempoEspera() + "\t"
            + "TRespuesta: " + p.getTiempoRespuesta() + "\n"
         );
         
         
         
         
         //Al finalizar
         terminados();
        
         numeroProcesos--;
         procesos.remove(0);
         aListos();
         procesosListos--;

          
         if(procesosListos<0){
             procesosListos=0;
         }
         
         lProcesosEspera.setText(Integer.toString(procesosListos));
     }
     
     //proceso nulo
     
     while(!bloqueados.isEmpty()){
         
         if(!procesos.isEmpty()){
             break;
         }
         
         
         mostrarBloqueados();
         tProcesoEjecucion.setText("");
         
         lContadorGlobal.setText(Integer.toString(contadorGlobal));
         contadorGlobal++;
         
         if(keyN){
             //Nuevos
              registrarNuevo();
              mostrarProcesos();
              keyN=false;
         }
         if(keyT){
             //Tabla
        meterDatosAlaTabla();
                 tablaDatos.setVisible(true);
                synchronized (this){
                   wait();
               }
               keyT=false;
                 
         }
         
         Thread.sleep(500);
         lContadorBloqueados.setText(Integer.toString(tiempoBloqueados));
         
         if(tiempoBloqueados==8 || tiempoBloqueados > 8){
             tiempoBloqueados=1;
             bloqueadoListo();
             mostrarBloqueados();
             break;
             
         }
         
         tiempoBloqueados++;
         
         
         
     }
     
     
     
     
     
     }
     limpiarTodo();
              
}
 
 
 public void carrusel(){
     /*
   private String id;
    private String nombre;
    private String op1;
    private String operators;
    private String op2;
    private String result;
    private String tiempoMaximo;
    private String lote;
    private String tiempoTranscurrido;
    private String tiempoFinalizacion;
    private String tiempoEspera;
    private String tiempoServicio;
    private String tiempoRetorno;
    private String tiempoRespuesta;
    private String tiempoLlegada;
    private String tiempoRestante;
    private String quantum;
    private String tiempoBloqueado;
     */
    String id,nombre,op1,operadores,op2,resultado,tm,lot,tt,tf,te,ts,tr,tres,tll,
            trest,q,tb;
    
    Procesos p=(Procesos)procesos.get(0);
    id=p.getId();
    nombre=p.getNombre();
    op1=p.getOp1();
    operadores=p.getOperators();
    op2=p.getOp2();
    resultado=p.getResult();
    tm=p.getTiempoMaximo();
    lot="";
    tt=p.getTiempoTranscurrido();
    tf=p.getTiempoFinalizacion();
    te=p.getTiempoEspera();
    ts=p.getTiempoServicio();
    tr=p.getTiempoRetorno();
    tres=p.getTiempoRespuesta();
    tll=p.getTiempoLlegada();
    trest=p.getTiempoRestante();
    q=p.getQuantum();
    tb="";
    
    procesos.remove(0);
    
    procesos.add(new Procesos(
    id,nombre,op1,operadores,op2,resultado,tm,lot,
            tt,tf,te,ts,tr,tres,tll,trest,q,tb
    ));
    
    
    
    
    
     
     
 }
 
 
 public void registrarNuevo(){
     numeroProcesos++;
     
        int op1, op2, op, tiempoMaximo;
        String[] operadores = {"+", "/", "*", "%", "+", "sqrt", "-"};
        op1 = (int) (Math.random() * 25 + 1);
        op = (int) (Math.random() * 2 + 0);
        op2 = (int) (Math.random() * 25 + 1);
        tiempoMaximo = (int) (Math.random() * 15 + 5);
        

                    
           if (numeroProcesos <=4) {

                procesos.add(new Procesos(
                        Integer.toString(id),
                        "",Integer.toString(op1),operadores[op],
                        Integer.toString(op2),"", //operaciones
                        Integer.toString(tiempoMaximo),"",
                        "","","","","","",Integer.toString(contadorGlobal),
                        Integer.toString(tiempoMaximo),
                        Integer.toString(quantum),"0" //tiempos
                
                ));
               

            }
           else{
               
               procesosListos+=1;
               lProcesosEspera.setText(Integer.toString(procesosListos));
               
               listos.add(new Procesos(
                        Integer.toString(id),
                        "",Integer.toString(op1),operadores[op],
                        Integer.toString(op2),"", //operaciones
                        Integer.toString(tiempoMaximo),"",
                        "","","","","","",Integer.toString(contadorGlobal),
                       Integer.toString(tiempoMaximo),
                        Integer.toString(quantum),"0" //tiempos
               
               ));
               
               
           }
           
           
           
           id++;
        
     
     
 }
 
 
public void terminados(){
    Procesos p =(Procesos)procesos.get(0);
    terminados.add(new Procesos(
                p.getId(),p.getNombre(),p.getOp1(),p.getOperators(),
                    p.getOp2(),p.getResult(),p.getTiempoMaximo(),p.getLote(),
                    p.getTiempoTranscurrido(),p.getTiempoFinalizacion(),
                    p.getTiempoEspera(),p.getTiempoServicio(),p.getTiempoRetorno(),
                    p.getTiempoRespuesta(),p.getTiempoLlegada(),
                    p.getTiempoRestante(),p.getQuantum(),p.getTiempoBloqueado()
    
    
    
    ));
}
 
public void limpiarTodo(){
    lProcesosEspera.setText("0");
    lContadorBloqueados.setText("0");
    lContadorBloqueados.setText("0");
    lTiempoRestante.setText("0");
    ltiempoTranscurrido.setText("0");
    lQuantum.setText("0");
    
    tProcesosListos.setText("");
    tBloqueados.setText("");
    tProcesoEjecucion.setText("");
    
    numeroProcesos=0;
    contadorGlobal=0;
    id=0;
    procesosListos=0;
    idAux="";
    
    procesos.clear();
    listos.clear();
    bloqueados.clear();
    terminados.clear();
}
 
 public void tiempoEspera(){
     int ts,tr,te;
     Procesos p= (Procesos)procesos.get(0);
     
     ts=Integer.parseInt(p.getTiempoServicio());
     tr=Integer.parseInt(p.getTiempoRetorno());
     
     te=(tr-ts);
     
     if(te<=0){
         p.setTiempoEspera("0");
     }
     else{
         p.setTiempoEspera(Integer.toString(te));
     }
     
     
 }
 
 
public void tiempoRetorno(){
    int tf,tll,tr;
    Procesos p= (Procesos)procesos.get(0);
    
    tf=Integer.parseInt(p.getTiempoFinalizacion());
    tll=Integer.parseInt(p.getTiempoLlegada());
    
    tr=tf-tll;
    
    p.setTiempoRetorno(Integer.toString(tr));
    
    
    
    
}
 
 
 
public void mostrarBloqueados(){
        tBloqueados.setText("");
        for (int i = 0; i < bloqueados.size(); i++) {

            tBloqueados.append("ID: " + bloqueados.get(i).getId() + "    "
                    + "TR: " + bloqueados.get(i).getTiempoRestante()+ "    "
                    + "TME: " + bloqueados.get(i).getTiempoMaximo()+ "    "
                    +"Q:  "+bloqueados.get(i).getQuantum()+"\n"
                    
                    );
        }
}
 
 
public void bloqueadoListo(){
    
    if (!bloqueados.isEmpty()) {
            Procesos p= (Procesos)bloqueados.get(0); 
            
            procesos.add(new Procesos(
            p.getId(),p.getNombre(),p.getOp1(),p.getOperators(),
            p.getOp2(),p.getResult(),p.getTiempoMaximo(),p.getLote(),
                    p.getTiempoTranscurrido(),p.getTiempoFinalizacion(),
                    p.getTiempoEspera(),p.getTiempoServicio(),p.getTiempoRetorno(),
                    p.getTiempoRespuesta(),p.getTiempoLlegada(),
                    p.getTiempoRestante(),p.getQuantum(),p.getTiempoBloqueado()
            ));

            bloqueados.remove(0);

        }
    
    
    
    
} 
 
 
 
public void aListos(){
           

        if (!listos.isEmpty()) {
            Procesos p= (Procesos)listos.get(0); 
            procesos.add(new Procesos(
            p.getId(),p.getNombre(),p.getOp1(),p.getOperators(),
            p.getOp2(),p.getResult(),p.getTiempoMaximo(),p.getLote(),
                    p.getTiempoTranscurrido(),p.getTiempoFinalizacion(),
                    p.getTiempoEspera(),p.getTiempoServicio(),p.getTiempoRetorno(),
                    p.getTiempoRespuesta(),Integer.toString(contadorGlobal),
                    p.getTiempoRestante(),p.getQuantum(),p.getTiempoBloqueado()
            ));

            listos.remove(0);

        }
}
 
 
public String operacion(int aux){
            String signo = procesos.get(aux).getOperators();
        int op1 = Integer.parseInt(procesos.get(aux).getOp1());
        int op2 = Integer.parseInt(procesos.get(aux).getOp2());
        int res;

        if (signo.equals("+")) {
            res = op1 + op2;
        } else if (signo.equals("-")) {
            res = op1 - op2;

        } else if (signo.equals("*")) {
            res = op1 * op2;
        } else if (signo.equals("/")) {
            res = op1 / op2;
        } else if (signo.equals("%")) {
            res = op1 % op2;
        } else if (signo.equals("potencia")) {
            res = (int) Math.pow(op1, op2);
        } else if (signo.equals("sqrt")) {
            res = (int) Math.sqrt(op2);
        } else {
            res = 0;
        }
        return Integer.toString(res);
    
    
    
}
 
 public void calcularTiempoRespuesta(int tiempoRespuesta){
     Procesos p= (Procesos)procesos.get(0);
        if(p.getTiempoRespuesta().equals("")){
             if(p.getId().equals("0")){
                 p.setTiempoRespuesta("0");
             }
             else{
                 int TLL= Integer.parseInt(p.getTiempoLlegada());
                 int res= tiempoRespuesta-TLL;
                 //negativos
                 if(res<0){
                     res= TLL-tiempoRespuesta;
                     
                 }
                 p.setTiempoRespuesta(Integer.toString(res));
             }
         }
         
 }
    
    
    public void mostrarProcesos(){
        tProcesosListos.setText("");
        for(int i=0; i<procesos.size(); i++){
            
          if(!(idAux.equals(procesos.get(i).getId()))){
            tProcesosListos.append("ID: "+procesos.get(i).getId()+"   "
            +"TR: "+procesos.get(i).getTiempoRestante()+"   "
            +"TME: "+procesos.get(i).getTiempoMaximo()+"   "
            +"Q: "+procesos.get(i).getQuantum()+"\n" );

            }
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lContadorBloqueados;
    private javax.swing.JLabel lContadorGlobal;
    private javax.swing.JLabel lProcesosEspera;
    private javax.swing.JLabel lQuantum;
    private javax.swing.JLabel lTiempoRestante;
    private javax.swing.JLabel ltiempoTranscurrido;
    private javax.swing.JTextArea tBloqueados;
    private javax.swing.JTextField tNumeroProcesos;
    private javax.swing.JTextArea tProcesoEjecucion;
    private javax.swing.JTextArea tProcesosListos;
    private javax.swing.JTextField tQuantum;
    private javax.swing.JTextArea tTerminados;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables
}
