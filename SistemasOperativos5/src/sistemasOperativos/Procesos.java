/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasOperativos;

/**
 *
 * @author javier
 */
public class Procesos {

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

    public Procesos(String id, String nombre, String op1, String operators, String op2, String result, String tiempoMaximo, String lote, String tiempoTranscurrido, String tiempoFinalizacion, String tiempoEspera, String tiempoServicio, String tiempoRetorno, String tiempoRespuesta, String tiempoLlegada, String tiempoRestante, String quantum, String tiempoBloqueado) {
        this.id = id;
        this.nombre = nombre;
        this.op1 = op1;
        this.operators = operators;
        this.op2 = op2;
        this.result = result;
        this.tiempoMaximo = tiempoMaximo;
        this.lote = lote;
        this.tiempoTranscurrido = tiempoTranscurrido;
        this.tiempoFinalizacion = tiempoFinalizacion;
        this.tiempoEspera = tiempoEspera;
        this.tiempoServicio = tiempoServicio;
        this.tiempoRetorno = tiempoRetorno;
        this.tiempoRespuesta = tiempoRespuesta;
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoRestante = tiempoRestante;
        this.quantum = quantum;
        this.tiempoBloqueado = tiempoBloqueado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(String tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public void setTiempoTranscurrido(String tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public String getTiempoFinalizacion() {
        return tiempoFinalizacion;
    }

    public void setTiempoFinalizacion(String tiempoFinalizacion) {
        this.tiempoFinalizacion = tiempoFinalizacion;
    }

    public String getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(String tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public String getTiempoServicio() {
        return tiempoServicio;
    }

    public void setTiempoServicio(String tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

    public String getTiempoRetorno() {
        return tiempoRetorno;
    }

    public void setTiempoRetorno(String tiempoRetorno) {
        this.tiempoRetorno = tiempoRetorno;
    }

    public String getTiempoRespuesta() {
        return tiempoRespuesta;
    }

    public void setTiempoRespuesta(String tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }

    public String getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(String tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public String getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(String tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public String getQuantum() {
        return quantum;
    }

    public void setQuantum(String quantum) {
        this.quantum = quantum;
    }

    public String getTiempoBloqueado() {
        return tiempoBloqueado;
    }

    public void setTiempoBloqueado(String tiempoBloqueado) {
        this.tiempoBloqueado = tiempoBloqueado;
    }
    
    
    
    
    
}
