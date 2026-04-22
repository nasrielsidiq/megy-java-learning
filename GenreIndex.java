public class GenreIndex {

    // 1. Inner Class untuk Linked List Buku (Pengganti ArrayList)
    private class BookNode {
        String title;
        BookNode next;

        BookNode(String title) {
            this.title = title;
        }
    }
    // example jika di input Booknode 
    // BookNode book1 = new BookNode("Harry Potter");
    // book1.next = new BookNode("The Lord of the Rings");
    // hasil data book1 -> "Harry Potter" -> "The Lord of the Rings" -> null

    // 2. Inner Class untuk Entry HashMap (Teknik Chaining)
    private class Entry {
        String genre;
        BookNode bookHead; // Kepala list buku
        int bookCount;     // Menghitung jumlah buku dalam genre ini
        Entry next;        // Pointer untuk collision di bucket yang sama

        Entry(String genre) {
            this.genre = genre;
            this.bookCount = 0;
        }
    }

    private Entry[] buckets;
    private int capacity;
    private int genreSize;

    public GenreIndex(int capacity) {
        this.capacity = capacity;
        this.buckets = new Entry[capacity];
        this.genreSize = 0;
    }

    private int getIndex(String genre) {
        return (genre == null) ? 0 : Math.abs(genre.hashCode()) % capacity;
    }

    // FEATURE: addBook (Menambahkan buku ke dalam genre)
    public void addBook(String genre, String bookTitle) {
        int index = getIndex(genre);
        Entry currentEntry = buckets[index];

        // Cari apakah genre sudah ada
        while (currentEntry != null) {
            if (currentEntry.genre.equalsIgnoreCase(genre)) {
                addBookToEntry(currentEntry, bookTitle);
                return;
            }
            currentEntry = currentEntry.next;
        }

        // Jika genre baru, buat Entry baru dan pasang di depan bucket (Chaining)
        Entry newEntry = new Entry(genre);
        addBookToEntry(newEntry, bookTitle);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        genreSize++;
    }

    // Helper untuk menambah buku ke dalam list internal Entry
    private void addBookToEntry(Entry entry, String title) {
        BookNode newNode = new BookNode(title);
        newNode.next = entry.bookHead;
        entry.bookHead = newNode;
        entry.bookCount++;
    }

    // FEATURE: getBooksInGenre
    public void getBooksInGenre(String genre) {
        int index = getIndex(genre);
        Entry current = buckets[index];

        while (current != null) {
            if (current.genre.equalsIgnoreCase(genre)) {
                System.out.println("=== Jadwal Buku Genre: " + current.genre + " ===");
                BookNode temp = current.bookHead;
                int i = 1;
                while (temp != null) {
                    System.out.println(i + ". " + temp.title);
                    temp = temp.next;
                    i++;
                }
                return;
            }
            current = current.next;
        }
        System.out.println("Tidak ditemukan genre: " + genre);
    }

    // FEATURE: removeGenre
    public void removeGenre(String genre) {
        int index = getIndex(genre);
        Entry current = buckets[index];
        Entry prev = null;

        while (current != null) {
            if (current.genre.equalsIgnoreCase(genre)) {
                if (prev == null) buckets[index] = current.next;
                else prev.next = current.next;
                
                genreSize--;
                System.out.println("Genre " + genre + " berhasil dihapus.");
                return;
            }
            prev = current;
            current = current.next;
        }
        System.out.println("Gagal menghapus: " + genre + " tidak ditemukan.");
    }

    // FEATURE: listGenres
    public void listGenres() {
        System.out.println("=== Daftar Genre ===");
        for (int i = 0; i < capacity; i++) {
            Entry e = buckets[i];
            while (e != null) {
                System.out.println("- " + e.genre + " : " + e.bookCount + " buku");
                e = e.next;
            }
        }
    }

    // FEATURE: size
    public int size() {
        return genreSize;
    }
}