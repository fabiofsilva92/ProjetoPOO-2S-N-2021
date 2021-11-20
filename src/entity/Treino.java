package entity;

public class Treino {

    private Long id;
    private String nomeAluno;
    private String sobreNome;
    private String tipo;
    private String musculo;
    private int aparelho;
    private int repeticoesQTD;
    private int repeticoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMusculo() {
        return musculo;
    }

    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }

    public int getAparelho() {
        return aparelho;
    }

    public void setAparelho(int aparelho) {
        this.aparelho = aparelho;
    }

    public int getRepeticoesQTD() {
        return repeticoesQTD;
    }

    public void setRepeticoesQTD(int repeticoesQTD) {
        this.repeticoesQTD = repeticoesQTD;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }
}
