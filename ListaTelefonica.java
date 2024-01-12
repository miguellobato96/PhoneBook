import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaTelefonica {
    private List<Contato> contatos;
    private static final String CSV_FILE_PATH = "contatos.csv";

    public ListaTelefonica() {
        this.contatos = new ArrayList<>();
        loadCSV();
    }

    public void adicionarContato(Contato contato) {
        if (!contatos.contains(contato)) {
            contatos.add(contato);
            saveCSV();
            System.out.println("Contato adicionado: " + contato);
        } else {
            System.out.println("Contato já existe na lista.");
        }
    }

    public void removerContato(Contato contato) {
        contatos.remove(contato);
        saveCSV();
        System.out.println("Contato removido: " + contato);
    }

    public void listarContatos() {
        for (Contato contato : contatos) {
            System.out.println(contato);
        }
    }

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

            saveCSV();
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

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
                System.err.println("Erro ao carregar de CSV: " + e.getMessage());
            }
        } else {
            System.out.println("Criando novo arquivo CSV: " + CSV_FILE_PATH);
        }
    }

    public static void main(String[] args) {
        ListaTelefonica lista = new ListaTelefonica();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Remover Contato");
            System.out.println("3. Listar Contatos");
            System.out.println("4. Buscar Contato por Nome");
            System.out.println("5. Modificar Contato");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
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
                    System.out.print("Nome do Contato a ser removido: ");
                    String nomeRemover = scanner.nextLine();
                    lista.removerContato(lista.procurarContato("nome", nomeRemover));
                    break;
                case 3:
                    lista.listarContatos();
                    break;
                case 4:
                    System.out.print("Nome do Contato a ser buscado: ");
                    String nomeProcurar = scanner.nextLine();
                    Contato contatoEncontrado = lista.procurarContato("nome", nomeProcurar);
                    if (contatoEncontrado != null) {
                        System.out.println("Contato encontrado: " + contatoEncontrado);
                    } else {
                        System.out.println("Contato não encontrado.");
                    }
                    break;
                case 5:
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
