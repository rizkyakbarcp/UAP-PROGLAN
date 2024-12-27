
## Aplikasi Laundry  

berikut adalah sebuah program berbasis java swing yang bisa digunakan untuk mengelola laundry, dilengkapi dengan beberapa fitur yaitu input pesanan, jenis cucian, kalkulasi biaya, pencentakan struk, penyimpanan data ke file.

 ## Fitur Utama
1. **Input Pesanan**:
   - Memasukkan nama pelanggan, berat cucian (kg), dan jenis cucian.
2. **Jenis Cucian**:
   - Pilihan jenis cucian:
     - **Biasa**: Rp 5,000/kg
     - **Delicate**: Rp 8,000/kg
     - **Besar (Bedcover, Selimut)**: Rp 10,000/kg
3. **Daftar Pesanan**:
   - Tabel yang menampilkan daftar pesanan lengkap dengan perhitungan total biaya.
4. **Cetak Struk**:
   - Menampilkan struk pesanan dalam dialog.
5. **Simpan Data**:
   - Menyimpan data pesanan ke file `laundry_data.txt`.

## Prasyarat
1. **Java Development Kit (JDK)** versi 8 atau lebih baru.

## Cara Menggunakan
1. **Jalankan Program**:
   ```bash
   java LaundryAppWithBackground
   ```
2. **Input Pesanan**:
   - Isi nama pelanggan, berat cucian, dan pilih jenis cucian.
   - Klik tombol **Tambah Pesanan** untuk menambahkan ke daftar.
3. **Cetak Struk**:
   - Klik tombol **Cetak Struk** untuk menampilkan struk pesanan.
4. **Simpan Data**:
   - Klik tombol **Simpan Data** untuk menyimpan pesanan ke file `laundry_data.txt`.
5. **Hapus Semua**:
   - Klik tombol **Hapus Semua** untuk mengosongkan daftar pesanan.

## Struktur Proyek
```
.
├── LaundryAppWithBackground.java   # File kode sumber utama
└── laundry_data.txt                # File keluaran untuk data pesanan (dihasilkan setelah dijalankan)
```

## Lisensi
Aplikasi ini dirancang untuk keperluan belajar dan dapat digunakan bebas dengan menyertakan kredit kepada pengembang asli.
