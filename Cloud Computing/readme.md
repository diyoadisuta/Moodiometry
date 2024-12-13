# Moodiometry

Moodiometry adalah aplikasi berbasis cloud yang dirancang untuk melacak suasana hati dan tingkat stres pengguna. Dengan fitur pengingat, rekomendasi kegiatan, dan layanan konsultasi, aplikasi ini bertujuan untuk mendukung kesehatan mental dan meningkatkan produktivitas pengguna.

## Fitur Utama
- Pelacakan Suasana Hati dan Stres: Rekam suasana hati pengguna setiap hari.
- Rekomendasi Kegiatan: Aktivitas yang dirancang untuk mengurangi stres.
- Pengingat Kesehatan Mental: Notifikasi untuk menjaga keseimbangan kerja dan kesehatan.
- Konsultasi Profesional: Akses ke layanan konsultasi kesehatan mental.

## Prerequisites
Untuk menjalankan proyek ini, Anda membutuhkan:
- Akun Google Cloud Platform dengan kredit aktif.
- Node.js versi 16 atau lebih baru.
- npm untuk mengelola paket.
- Git untuk mengelola versi kode.

## Getting Started
Ikuti langkah-langkah berikut untuk menjalankan proyek Moodiometry di mesin Anda:

### Clone Repository
1. Clone repository ke lokal Anda:
   git clone https://github.com/diyoadisuta/Moodiometry.git
   cd moodiometry

### Install Dependencies
2. Instal semua dependencies yang dibutuhkan:
   npm install

### Deploy to Google Cloud
3. Deploy aplikasi ke Google Cloud Run:
   Build dan deploy aplikasi menggunakan Google Cloud CLI
   - Build container image :
     gcloud builds submit --tag gcr.io/moodiometry
   - Deploy ke Cloud Run :
     gcloud run deploy modelapi \
     --image gcr.io/moodiometry \
     --platform managed \
     --region asia-southeast2 \
     --allow-unauthenticated
