import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Book implements Serializable {
    String title, author;
    boolean isBorrowed;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String toString() {
        return title + " by " + author + (isBorrowed ? " (Borrowed)" : " (Available)");
    }
}

class User implements Serializable {
    String name;
    ArrayList<String> borrowedTitles = new ArrayList<>();

    User(String name) {
        this.name = name;
    }

    public String toString() {
        return name + " (Books: " + borrowedTitles.size() + ")";
    }
}

public class LibraryManagementSystem extends JFrame {
    private DefaultListModel<Book> bookModel = new DefaultListModel<>();
    private DefaultListModel<User> userModel = new DefaultListModel<>();
    private JList<Book> bookList = new JList<>(bookModel);
    private JList<User> userList = new JList<>(userModel);
    private JTextField searchField = new JTextField(15);

    public LibraryManagementSystem() {
        setTitle("Library Management System (Advanced)");
        setSize(1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        JPanel bookPanel = new JPanel(new BorderLayout());
        bookPanel.setBorder(BorderFactory.createTitledBorder("Books"));
        bookPanel.add(new JScrollPane(bookList), BorderLayout.CENTER);

        JPanel bookControl = new JPanel();
        JTextField titleField = new JTextField(10);
        JTextField authorField = new JTextField(10);
        JButton addBook = new JButton("Add Book");
        bookControl.add(new JLabel("Title:"));
        bookControl.add(titleField);
        bookControl.add(new JLabel("Author:"));
        bookControl.add(authorField);
        bookControl.add(addBook);
        bookPanel.add(bookControl, BorderLayout.SOUTH);

        JPanel userPanel = new JPanel(new BorderLayout());
        userPanel.setBorder(BorderFactory.createTitledBorder("Users"));
        userPanel.add(new JScrollPane(userList), BorderLayout.CENTER);

        JPanel userControl = new JPanel();
        JTextField nameField = new JTextField(10);
        JButton addUser = new JButton("Add User");
        userControl.add(new JLabel("Name:"));
        userControl.add(nameField);
        userControl.add(addUser);
        userPanel.add(userControl, BorderLayout.SOUTH);

        mainPanel.add(bookPanel);
        mainPanel.add(userPanel);

        JPanel actionPanel = new JPanel();
        JButton borrowBtn = new JButton("Borrow Book");
        JButton returnBtn = new JButton("Return Book");
        JButton saveBtn = new JButton("Save Records");
        JButton loadBtn = new JButton("Load Records");
        JButton clearBtn = new JButton("Clear All");

        actionPanel.add(new JLabel("Search:"));
        actionPanel.add(searchField);
        actionPanel.add(borrowBtn);
        actionPanel.add(returnBtn);
        actionPanel.add(saveBtn);
        actionPanel.add(loadBtn);
        actionPanel.add(clearBtn);

        add(mainPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // Actions
        addBook.addActionListener(e -> {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            if (!title.isEmpty() && !author.isEmpty()) {
                bookModel.addElement(new Book(title, author));
                titleField.setText("");
                authorField.setText("");
            }
        });

        addUser.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                userModel.addElement(new User(name));
                nameField.setText("");
            }
        });

        borrowBtn.addActionListener(e -> {
            Book book = bookList.getSelectedValue();
            User user = userList.getSelectedValue();
            if (book != null && user != null && !book.isBorrowed) {
                book.isBorrowed = true;
                user.borrowedTitles.add(book.title);
                bookList.repaint();
                userList.repaint();
            }
        });

        returnBtn.addActionListener(e -> {
            Book book = bookList.getSelectedValue();
            User user = userList.getSelectedValue();
            if (book != null && user != null && book.isBorrowed && user.borrowedTitles.contains(book.title)) {
                book.isBorrowed = false;
                user.borrowedTitles.remove(book.title);
                bookList.repaint();
                userList.repaint();
            }
        });

        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText().toLowerCase();
                DefaultListModel<Book> filtered = new DefaultListModel<>();
                for (int i = 0; i < bookModel.size(); i++) {
                    Book b = bookModel.get(i);
                    if (b.title.toLowerCase().contains(query) || b.author.toLowerCase().contains(query)) {
                        filtered.addElement(b);
                    }
                }
                bookList.setModel(filtered);
            }
        });

        saveBtn.addActionListener(e -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("library_data.dat"))) {
                ArrayList<Book> books = Collections.list(bookModel.elements());
                ArrayList<User> users = Collections.list(userModel.elements());
                oos.writeObject(books);
                oos.writeObject(users);
                JOptionPane.showMessageDialog(this, "Data saved successfully.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        loadBtn.addActionListener(e -> {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("library_data.dat"))) {
                ArrayList<Book> books = (ArrayList<Book>) ois.readObject();
                ArrayList<User> users = (ArrayList<User>) ois.readObject();
                bookModel.clear();
                userModel.clear();
                books.forEach(bookModel::addElement);
                users.forEach(userModel::addElement);
                bookList.setModel(bookModel);
                userList.setModel(userModel);
                JOptionPane.showMessageDialog(this, "Data loaded successfully.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        clearBtn.addActionListener(e -> {
            bookModel.clear();
            userModel.clear();
            searchField.setText("");
        });
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem().setVisible(true));
    }
}
