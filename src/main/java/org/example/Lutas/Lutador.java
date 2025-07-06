package org.example.Lutas;

import java.util.Random;

public class Lutador {
    private String nome, nacionalidade;
    private int idade;
    private double altura, peso, indiceDePerformance;
    private String categoria;
    private int vitorias, derrotas, empates;

    public void apresentar(){
        System.out.println("-------------------------------------");
        System.out.println("CHEGOU A HORA! Apresentamos o lutador " + this.getNome());
        System.out.println("Diretamente de " + this.getNacionalidade());
        System.out.println("Com " + this.getIdade() + " anos e " + this.getAltura() + "m");
        System.out.println("Pesando " + this.getPeso() + "kg");
        System.out.println(this.getVitorias() + " vitórias!");
        System.out.println(this.getEmpates() + " empates!");
        System.out.println(this.getDerrotas() + " derrotas!");
    }
    public void status(){
        System.out.println(this.getNome() + " é categoria " + this.getCategoria());
        System.out.println("Ganhou " + this.getVitorias() + " vezes");
        System.out.println("Empatou " + this.getEmpates() + " vezes");
        System.out.println("Perdeu " + this.getDerrotas() + " vezes");
        System.out.println("\n");
    }
    public void ganharLuta(){
        this.setVitorias(this.getVitorias() + 1);
    }
    public void perderLuta(){
        this.setDerrotas(this.getDerrotas() + 1);
    }
    public void empatarLutar(){
        this.setEmpates(this.getEmpates() + 1);
    }


    public Lutador(String nome, String nacionalidade, int idade, double altura, double peso, int vitorias, int derrotas, int empates) {
        this.setNome(nome);
        this.setNacionalidade(nacionalidade);
        this.setIdade(idade);
        this.setAltura(altura);
        this.setPeso(peso);
        this.setVitorias(vitorias);
        this.setDerrotas(derrotas);
        this.setEmpates(empates);
        this.setIndiceDePerformance();
    }

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public int getIdade() {
        return idade;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public double getIndiceDePerformance() {
        return indiceDePerformance;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
        this.setCategoria();
    }

    private void setCategoria() {
        if(this.getPeso() < 52.2){
            this.categoria = "Inválido";
        } else if(this.getPeso() <= 70.3){
            this.categoria = "Leve";
        } else if(this.getPeso() <= 83.9){
            this.categoria = "Médio";
        } else if(this.getPeso() <= 120.2){
            this.categoria = "Pesado";
        } else{
            this.categoria = "Inválido";
        }
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    private void setIndiceDePerformance() {
        double IP = (this.getAltura() * 10) + (this.getPeso() * 0.3) + (this.getVitorias() * 5) - (this.getDerrotas() * 2) - (Math.abs(this.getIdade() - 28) * 1.5);
        Random fatorAleatorio = new Random();
        double resultadoFinal = IP + (fatorAleatorio.nextInt(50) - 15);
        this.indiceDePerformance = resultadoFinal;
    }
}
