import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();

        int choice;
        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Adicionar Pessoa");
            System.out.println("2. Remover Pessoa");
            System.out.println("3. Modificar Pessoa");
            System.out.println("4. Procurar Pessoa");
            System.out.println("0. Sair");

            System.out.print("Opção: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addPerson(phoneBook, scanner);
                    break;
                case 2:
                    // Implemente a lógica para remover uma pessoa do livro de telefone
                    break;
                case 3:
                    // Implemente a lógica para modificar os detalhes de uma pessoa no livro de telefone
                    break;
                case 4:
                    // Implemente a lógica para procurar uma pessoa no livro de telefone
                    break;
                case 0:
                    System.out.println("Saindo do programa. Adeus!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (choice != 0);
    }

    private static void addPerson(PhoneBook phoneBook, Scanner scanner) {
        System.out.print("Nome: ");
        String name = scanner.nextLine();

        System.out.print("Número de Telefone: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Morada: ");
        String address = scanner.nextLine();

        Person person = new Person(name, phoneNumber, email, address);
        phoneBook.addPerson(person);
    }
}
