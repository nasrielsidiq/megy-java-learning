public class Main {
    public static void main(String[] args) {
        GenreIndex genreIndex = new GenreIndex(5);

        // Menambahkan genre dan buku
        genreIndex.addBook("Fantasy", "Harry Potter");
        genreIndex.addBook("Fantasy", "The Lord of the Rings");
        genreIndex.addBook("Science Fiction", "Dune");
        genreIndex.addBook("Science Fiction", "Neuromancer");
        genreIndex.addBook("Mystery", "The Da Vinci Code");

        // Menampilkan buku dalam genre tertentu
        genreIndex.getBooksInGenre("Fantasy");
        genreIndex.getBooksInGenre("Science Fiction");

        // Menampilkan semua genre
        genreIndex.listGenres();

        // Menghapus sebuah genre
        genreIndex.removeGenre("Mystery");

        // Menampilkan semua genre setelah penghapusan
        genreIndex.listGenres();
    }
}
