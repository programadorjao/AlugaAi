import model.Categoria;
import model.CategoriaService;
import model.Produto;
import model.Usuario;

public class Main {
    public static void main(String[] args) {

        CategoriaService service = new CategoriaService();

        service.adicionarCategoria("Automóveis");
        service.adicionarCategoria("Imóveis");
        service.adicionarCategoria("Casa e Decoração");
        service.adicionarCategoria("Móveis");
        service.adicionarCategoria("Construção");
        service.adicionarCategoria("Eletro");
        service.adicionarCategoria("Celulares");
        service.adicionarCategoria("Informática");
        service.adicionarCategoria("Games");
        service.adicionarCategoria("Tvs");
        service.adicionarCategoria("Agro");
        service.adicionarCategoria("Serviços");


        Usuario cliente1 = new Usuario("João Ferreira", "jn@gmail.com", "jn12345", "83991876113");
        Usuario cliente2 = new Usuario("João Marcos", "jm@gmail.com", "jm12000", "83994354492");
        Produto p1 = new Produto("furadeira", "boa toda", 25.0, service.buscarPorNome("Eletro"));

        cliente1.verificarCadastro();
        System.out.println(cliente1.getId());
        System.out.println(cliente2.getId());
        System.out.println(p1.getCategoria().getNome());

        if (p1.produtoValido()) {
            System.out.println("Cadastrado");
        }
    }
}