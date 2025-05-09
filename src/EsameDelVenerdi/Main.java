package EsameDelVenerdi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ElementoMultimediale[] elementi = new ElementoMultimediale[5];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < elementi.length; i++) {
            int tipo = -1;

            // Richiesta tipo elemento con gestione errore
            while (true) {
                System.out.println("Inserisci tipo elemento (1=Audio, 2=Video, 3=Immagine): ");
                try {
                    tipo = Integer.parseInt(scanner.nextLine());
                    if (tipo >= 1 && tipo <= 3) break;
                    System.out.println("Inserisci un numero tra 1 e 3.");
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero.");
                }
            }

            System.out.println("Titolo: ");
            String titolo = scanner.nextLine();

            try {
                switch (tipo) {
                    case 1 -> {
                        System.out.println("Durata: ");
                        int durataAudio = Integer.parseInt(scanner.nextLine());
                        System.out.println("Volume: ");
                        int volumeAudio = Integer.parseInt(scanner.nextLine());
                        elementi[i] = new RegistrazioneAudio(titolo, durataAudio, volumeAudio);
                    }

                    case 2 -> {
                        System.out.println("Durata: ");
                        int durataVideo = Integer.parseInt(scanner.nextLine());
                        System.out.println("Volume: ");
                        int volumeVideo = Integer.parseInt(scanner.nextLine());
                        System.out.println("Luminosità: ");
                        int luminositaVideo = Integer.parseInt(scanner.nextLine());
                        elementi[i] = new Video(titolo, durataVideo, volumeVideo, luminositaVideo);
                    }

                    case 3 -> {
                        System.out.println("Luminosità: ");
                        int luminositaImg = Integer.parseInt(scanner.nextLine());
                        elementi[i] = new Immagine(titolo, luminositaImg);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore nell'inserimento dei dati numerici. Ripeti l'inserimento dell'elemento.");
                i--; // ripeti lo stesso ciclo
            }
        }

        int scelta = -1;
        do {
            System.out.println("Scegli un elemento da eseguire (1-5), 0 per uscire:");
            try {
                scelta = Integer.parseInt(scanner.nextLine());

                if (scelta >= 1 && scelta <= 5) {
                    ElementoMultimediale elem = elementi[scelta - 1];
                    if (elem instanceof Riproducibile riproducibile) {
                        riproducibile.play();
                    } else if (elem instanceof Immagine immagine) {
                        immagine.show();
                    }
                } else if (scelta != 0) {
                    System.out.println("Scelta non valida. Inserisci un numero da 1 a 5, oppure 0 per uscire.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input non valido. Inserisci un numero.");
            }
        } while (scelta != 0);

        scanner.close();
    }
}
