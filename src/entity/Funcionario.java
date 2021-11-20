package entity;

import java.time.LocalDate;

public class Funcionario {

    private long id = 0;
    private String nome = "";
    private String sobrenome = "";
    private String cpf = "";
    private LocalDate dataNascimento = LocalDate.now();
    private LocalDate dataInicio = LocalDate.now();
    private int ddd = 0;
    private long telefone = 0;
    //private TelefoneFunc telefoneFunc = new TelefoneFunc(0, 0);
    private String sexo = "";
    private double salario = 0;
    private String email = "";


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

   /*public TelefoneFunc getTelefoneFunc() {
        return telefoneFunc;
    }

    public void setTelefoneFunc(TelefoneFunc telefoneFunc) {
        this.telefoneFunc = telefoneFunc;
    }*/

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getSalario() { return salario; }
    public void setSalario(double salario) {this.salario = salario;}

    @Override
    public String toString() {
        return this.nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
}

