package model;
import java.time.Duration;
import java.time.LocalDateTime;

    public class Aluguel {
    private int id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String status;

    private Produto produto;
    private Usuario cliente;
    private Usuario dono;

    public void solicitarAluguel(){
        if(produto.estaDisponivel()){
            status = "Pendente";
        } else {
            System.out.println("Produto indisponível");
        }
    }

    public void aprovarAluguel(){
        status = "Aprovado";
        produto.alterarDisponibilidade(false);
    }

    public void finalizarAluguel(){
        status = "Finalizado";
        produto.alterarDisponibilidade(true);
    }

    public void cancelarAluguel(){
        status = "Cancelado";
    }

    public double calcularValor() {
        if (dataInicio == null || dataFim == null) {
            System.out.println("Datas Inválidas");
            return 0;
        }

        if (dataFim.isBefore(dataInicio)) {
            System.out.println("Data final inválida");
            return 0;
        }

        Duration duracao = Duration.between(dataInicio, dataFim);

        long horas = duracao.toHours();

        double dias = horas / 24.0;

        //return dias * produtogetPrecoPorDia();
        return 0;
    }
    }