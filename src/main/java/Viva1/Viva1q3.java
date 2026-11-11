/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Viva1;

import java.util.Scanner;

/**
 *
 * @author YongWenGuan
 */
public class Viva1q3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int parcelsProcessed = 0;
        double totalFees = 0;
        int heaviestWeight = 0;

        while (true) {
            System.out.print("Enter destination postcode (or EXIT to stop): ");
            String postCode = in.nextLine();

            if (postCode.toUpperCase().equals("EXIT")) {
                break;
            }

            String pattern = "\\d{2}x\\d{2}x\\d{2}";
            String patternPostcode = "\\d{5}";

            String postCodeE = postCode.toUpperCase().replaceAll("[\\s-]|MY", "");
            if (postCodeE.length() < 2) {
                System.out.println("Error: Invalid postcode. Parcel skipped.\n");
                continue;
            }

            char first = postCodeE.charAt(0);
            char second = postCodeE.charAt(1);
            int firstTwo = (first - '0') * 10 + (second - '0');

            boolean isInternational = first - 'A' >= 0 && first - 'A' <= 25
                    && second - 'A' >= 0 && second - 'A' <= 25
                    && postCodeE.length() >= 3;
            boolean isDomestic = postCodeE.matches(patternPostcode)
                    && firstTwo >= 1 && firstTwo <= 98;

            if (!isInternational && !isDomestic) {
                System.out.println("Error: Invalid postcode. Parcel skipped.\n");
                continue;
            }

            int weightGrams = 0;
            while (true) {
                System.out.print("Enter parcel weight (g): ");
                if (!in.hasNextInt()) {
                    System.out.println("Error: Weight must be an integer.");
                    in.nextLine();
                    continue;
                }
                weightGrams = in.nextInt();
                in.nextLine();
                if (weightGrams <= 0) {
                    System.out.println("Error: Weight must be greater than 0.");
                } else {
                    break;
                }
            }

            double weight = weightGrams / 1000.0;
            double fee = 0;

            System.out.print("Enter dimensions LxWxH (cm): "); // assume only below 100cm is allowed
            String volume = in.next();
            in.nextLine();

            String adjustment = "None";

            while (!volume.matches(pattern)) {
                System.out.println("Error: Use the format LxWxH with positive numbers, e.g. 30x20x10.");
                System.out.print("Enter dimensions LxWxH (cm): ");
                volume = in.next();
                in.nextLine();
            }

            String zone;
            double volumeWeight = ((volume.charAt(0) - '0') * 10 + (volume.charAt(1) - '0'))
                    * ((volume.charAt(3) - '0') * 10 + (volume.charAt(4) - '0'))
                    * ((volume.charAt(6) - '0') * 10 + (volume.charAt(7) - '0'))
                    / 5000.0;
            int chargeableWeight = (int) Math.ceil((weight > volumeWeight) ? weight : volumeWeight);

            // international
            if (isInternational) {
                fee = chargeableWeight * 35;
                zone = "International (" + first + second + ")";
            } else {
                if (firstTwo >= 50 && firstTwo <= 60) {
                    fee = 6 + (chargeableWeight - 1) * 1.5;
                    zone = "Klang Valley";
                } else if ((firstTwo >= 1 && firstTwo <= 49) || (firstTwo >= 61 && firstTwo <= 86)) {
                    fee = 8 + (chargeableWeight - 1) * 2;
                    zone = "Peninsular";
                } else {
                    fee = 14 + (chargeableWeight - 1) * 4.5;
                    zone = "East Malaysia";
                }
            }

            if (chargeableWeight >= 5 && chargeableWeight <= 10) {
                fee *= 0.95;
                adjustment = "5% bulk discount";
            } else if (chargeableWeight > 10) {
                fee *= 1.15;
                adjustment = "15% heavy parcel surcharge";
            }

            parcelsProcessed++;
            totalFees += fee;
            if (chargeableWeight > heaviestWeight) {
                heaviestWeight = chargeableWeight;
            }

            System.out.println("\nShipping Summary");
            System.out.println("Postcode: " + postCodeE);
            System.out.println("Zone: " + zone);
            System.out.printf("Actual Weight: %.2f kg | Volumetric Weight: %.2f kg\n", weight, volumeWeight);
            System.out.println("Chargeable Weight: " + chargeableWeight + " kg");
            System.out.println("Adjustment: " + adjustment);
            System.out.printf("Shipping Fee: RM %.2f\n\n", fee);
        }

        System.out.println("Parcels Processed: " + parcelsProcessed);
        System.out.printf("Total Shipping Fees: RM %.2f\n", totalFees);
        System.out.println("Heaviest Chargeable Weight: " + heaviestWeight + " kg");
    }
}