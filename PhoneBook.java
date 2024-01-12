import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private List<Person> phoneBook;

    public PhoneBook() {
        this.phoneBook = new ArrayList<>();
    }

    // Adiciona uma pessoa à lista telefónica
    public void addPerson(Person person) {
        if (!phoneBook.contains(person)) {
            phoneBook.add(person);
            System.out.println("Pessoa adicionada com sucesso.");
        } else {
            System.out.println("Pessoa já existe na lista telefónica.");
        }
    }

    // Remove uma pessoa da lista telefónica
    public void deletePerson(Person person) {
        if (phoneBook.contains(person)) {
            phoneBook.remove(person);
            System.out.println("Pessoa removida com sucesso.");
        } else {
            System.out.println("Pessoa não encontrada na lista telefónica.");
        }
    }

    // Modifica os detalhes de uma pessoa na lista telefónica
    public void modifyPerson(Person oldPerson, Person newPerson) {
        if (phoneBook.contains(oldPerson)) {
            int index = phoneBook.indexOf(oldPerson);
            phoneBook.set(index, newPerson);
            System.out.println("Detalhes da pessoa modificados com sucesso.");
        } else {
            System.out.println("Pessoa não encontrada na lista telefónica.");
        }
    }

    // Procura uma pessoa na lista telefónica por atributo
    public void searchPerson(String attribute, String value) {
        for (Person person : phoneBook) {
            switch (attribute.toLowerCase()) {
                case "nome":
                    if (person.getName().equalsIgnoreCase(value)) {
                        System.out.println(person);
                    }
                    break;
                case "numero":
                    if (person.getPhoneNumber().equalsIgnoreCase(value)) {
                        System.out.println(person);
                    }
                    break;
                case "email":
                    if (person.getEmail().equalsIgnoreCase(value)) {
                        System.out.println(person);
                    }
                    break;
                case "morada":
                    if (person.getAddress().equalsIgnoreCase(value)) {
                        System.out.println(person);
                    }
                    break;
            }
        }
    }

    // Salva a lista telefónica em um arquivo
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(phoneBook);
            System.out.println("Lista telefônica salva no arquivo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carrega a lista telefónica de um arquivo
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            phoneBook = (List<Person>) ois.readObject();
            System.out.println("Lista telefónica carregada do arquivo com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
