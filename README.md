# GithubUsers
 My Submission 2 - Fundamental Aplikasi Android - Github User's

## Fitur yang diujikan
Fitur yang harus ada pada aplikasi:

Search User
Syarat:
Pencarian user menggunakan data dari API berjalan dengan baik. (Selesai)
Pengguna dapat melihat halaman detail dari hasil daftar pencarian. (Selesai)

Detail User
Syarat:
Mempertahankan fitur informasi user (User Details) yang sudah ditampilkan pada Submission 1. (Selesai)
Menampilkan fragment List Follower & List Following yang diambil dari API. (Selesai)
Menggunakan TabLayout sebagai navigasi antara halaman List Follower dan List Following. (Selesai)

Terdapat indikator loading saat aplikasi memuat data. (Selesa)

Improvisasi :

Aplikasi sesuai standar.

Kode bersih.

Aplikasi bisa menjaga data ketika orientasi dari potrait ke landscape atau sebaliknya.

Menggunakan library networking Retrofit.

Menggunakan RoboPojoGenerator untuk membuat data class response dan di custom.

Menerapkan Android Architecture Component yaitu ViewModel dan LiveData.

Terdapat halaman utama users menggunakan endpoint https://api.github.com/users.

Merapikan file kedalam package directory.

Tema warna yang elegan.

Terdapat fitur Refresh di Halaman Utama.

terdapat fitur share di Halaman Detail User.
Yang ingin ditambahkan :

- merubah sumber data menjadi API

- menambahkan fitur pencarian user di halaman utama dan hasil dapat diklik menuju detail user

- fragment list follower dan following didalam detail user menggunakan tabLayout

- ada indikator loading ketika memuat data

## improvisasi Submission

Halaman List User :
- Gambar Menggunakan plugin Glide

Halaman Detail User :
- Gambar Menggunakan plugin Glide.
- Divider didalam Detail User.
- Divider terlihat di dark Mode.
- Mengubah Format angka Followers dan Following.
- Tombol back ke List User.
- ScrollView didalam Detail User.
- Tombol Share didalam Detail User.

Mengganti warna primary dan secondary brand pada Theme.

## Color
 color Source : https://coolors.co/038c99-199da1-cefbfa-a82c2c-de0d0d-ffd4d4-000000-ffffff

## Saran

Terima kasih telah sabar menunggu. Kami membutuhkan waktu untuk bisa memberikan feedback sekomprehensif mungkin kepada setiap peserta kelas. Untuk meningkatkan kualitas project submission yang dikirimkan, kamu dapat menerapkan beberapa saran berikut:

- Jangan lupa untuk memeriksa hasil review kode yang diberikan pada table Submitted App.
- Mulailah menerapkan View Binding dalam project, agar kode yang dituliskan sesuai dengan best practice yang disarankan.
- Mulailah untuk menerapkan konsep Material Design ketika mengembangkan proyek Aplikasi. Kamu bisa mengunjungi www.material.io sebagai referensi.
- Cobalah untuk mengelompokkan Class yang memiliki fungsi dan tanggung jawab yang sama dalam suatu package agar project yang dikembangkan memiliki struktur yang lebih rapih dan akan memudahkan kamu dalam melakukan maintenances kedepannya.
- Selalu perhatikan resources yang tidak pernah digunakan di dalam project karena akan mempengaruhi size APK nantinya. Kamu bisa memanfaatkan fitur Remove Unused Resource untuk menghapus resources yang tidak pernah digunakan.
- Agar memastikan project yang kamu buat terhindar dari segala bentuk warning ataupun error, kamu bisa melakukan inspeksi kode dengan mudah melalui Analyze → Inspect Code.