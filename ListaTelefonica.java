import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaTelefonica {
    private List<Contato> contatos;
    private static final String CSV_FILE_PATH = "contatos.csv";

    // Construtor da classe ListaTelefonica
    public ListaTelefonica() {
        this.contatos = new ArrayList<>();
        loadCSV(); // Carrega os contatos do arquivo CSV ao instanciar a lista telefónica
    }

    // Adiciona um novo contato à lista telefónica
    public void adicionarContato(Contato contato) {
        if (!contatos.contains(contato)) {
            contatos.add(contato);
            saveCSV(); // Guarda a lista telefónica no arquivo CSV após adicionar um contato
            System.out.println("Contato adicionado: " + contato);
        } else {
            System.out.println("Contato já existe na lista.");
        }
    }

    // Remove um contato da lista telefónica
    public void removerContato(Contato contato) {
        contatos.remove(contato);
        saveCSV(); // Salva a lista telefónica no arquivo CSV após remover um contato
        System.out.println("Contato removido: " + contato);
    }

    // Lista todos os contatos da lista telefónica
    public void listarContatos() {
        for (Contato contato : contatos) {
            System.out.println(contato);
        }
    }

    // Procura um contato com base em um atributo e valor específicos
    public Contato procurarContato(String atributo, String valor) {
        for (Contato contato : contatos) {
            switch (atributo.toLowerCase()) {
                case "nome":
                    if (contato.getNome().equalsIgnoreCase(valor)) {
                        return contato;
                    }
                    break;
                case "telefone":
                    if (contato.getTelefone().equalsIgnoreCase(valor)) {
                        return contato;
                    }
                    break;
                case "email":
                    if (contato.getEmail().equalsIgnoreCase(valor)) {
                        return contato;
                    }
                    break;
                case "morada":
                    if (contato.getMorada().equalsIgnoreCase(valor)) {
                        return contato;
                    }
                    break;
                default:
                    System.out.println("Atributo inválido. Escolha entre nome, telefone, email, ou morada.");
            }
        }
        return null;
    }

    // Modifica os dados de um contato específico
    public void modificarContato(String nome, Scanner scanner) {
        Contato contato = procurarContato("nome", nome);
        if (contato != null) {
            System.out.println("Escolha o atributo a modificar:");
            System.out.println("1. Nome");
            System.out.println("2. Telefone");
            System.out.println("3. Email");
            System.out.println("4. Morada");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    contato.setNome(novoNome);
                    System.out.println("Nome modificado para " + novoNome);
                    break;
                case 2:
                    System.out.print("Novo Telefone: ");
                    String novoTelefone = scanner.nextLine();
                    contato.setTelefone(novoTelefone);
                    System.out.println("Telefone modificado para " + novoTelefone);
                    break;
                case 3:
                    System.out.print("Novo Email: ");
                    String novoEmail = scanner.nextLine();
                    contato.setEmail(novoEmail);
                    System.out.println("Email modificado para " + novoEmail);
                    break;
                case 4:
                    System.out.print("Nova Morada: ");
                    String novaMorada = scanner.nextLine();
                    contato.setMorada(novaMorada);
                    System.out.println("Morada modificada para " + novaMorada);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            saveCSV(); // Salva a lista telefónica no arquivo CSV após modificar um contato
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    // Método para guardar a lista telefónica no arquivo CSV
    private void saveCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH))) {
            for (Contato contato : contatos) {
                writer.println(contato.getNome() + "," + contato.getTelefone() + "," + contato.getEmail() + "," + contato.getMorada());
            }
            System.out.println("Lista guardada em " + CSV_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Erro ao guardar CSV: " + e.getMessage());
        }
    }

    // Método para carregar a lista telefónica a partir do arquivo CSV
    private void loadCSV() {
        File file = new File(CSV_FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 4) {
                        Contato contato = new Contato(data[0], data[1], data[2], data[3]);
                        contatos.add(contato);
                    }
                }
                System.out.println("Lista carregada de " + CSV_FILE_PATH);
            } catch (IOException e) {
                System.err.println("Erro ao carregar CSV: " + e.getMessage());
            }
        } else {
            System.out.println("Criando novo arquivo CSV: " + CSV_FILE_PATH);
        }
    }

    // Método principal (main) que inicia o programa
    public static void main(String[] args) {
        ListaTelefonica lista = new ListaTelefonica();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            // Menu principal
            System.out.println("Menu:");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Remover Contato");
            System.out.println("3. Listar Contatos");
            System.out.println("4. Procurar Contato");
            System.out.println("5. Modificar Contato");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Adicionar um novo contato
                    System.out.print("Nome do Contato: ");
                    String nome = scanner.nextLine();
                    System.out.print("Telefone do Contato: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Email do Contato: ");
                    String email = scanner.nextLine();
                    System.out.print("Morada do Contato: ");
                    String morada = scanner.nextLine();
                    lista.adicionarContato(new Contato(nome, telefone, email, morada));
                    break;
                case 2:
                    // Remover um contato
                    System.out.print("Nome do Contato a ser removido: ");
                    String nomeRemover = scanner.nextLine();
                    lista.removerContato(lista.procurarContato("nome", nomeRemover));
                    break;
                case 3:
                    // Listar todos os contatos
                    lista.listarContatos();
                    break;
                case 4:
                    // Procurar um contato
                    String atributoProcurar;
                    do {
                        System.out.print("Atributo pelo qual deseja procurar ('nome', 'telefone', 'email', 'morada'): ");
                        atributoProcurar = scanner.nextLine();
                        if (atributoProcurar.equalsIgnoreCase("nome") || 
                            atributoProcurar.equalsIgnoreCase("telefone") || 
                            atributoProcurar.equalsIgnoreCase("email") || 
                            atributoProcurar.equalsIgnoreCase("morada")) {
                            break;
                        } else {
                            System.out.println("Atributo inválido. Escolha entre nome, telefone, email, ou morada.");
                        }
                    } while (true);

                    System.out.print("Digite o/a " + atributoProcurar + " do Contato a ser procurado: ");
                    String nomeProcurar = scanner.nextLine();

                    Contato contatoEncontrado = lista.procurarContato(atributoProcurar, nomeProcurar);
                    if (contatoEncontrado != null) {
                        System.out.println("Contato encontrado: " + contatoEncontrado);
                    } else {
                        System.out.println("Contato não encontrado.");
                    }
                    break;
                case 5:
                    // Modificar um contato
                    System.out.print("Nome do Contato a ser modificado: ");
                    String nomeModificar = scanner.nextLine();
                    lista.modificarContato(nomeModificar, scanner);
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
