package com.example.ocspclient;

import java.io.IOException;

import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.cert.ocsp.SingleResp;
import org.bouncycastle.operator.OperatorCreationException;

public class OCSPClient {
    public static void main(String[] args) {
        // Hard coded the provided byte array. Plucking could have been implemented using inputstream and url imports.
        byte[] ocspResponse = new byte[]{48, -126, 3, -71, 10, 1, 0, -96, -126, 3, -78,
            48, -126, 3, -82, 6, 9, 43, 6, 1, 5, 5, 7, 48, 1, 1, 4, -126, 3, -97,
            48, -126, 3, -101, 48, -127, -97, -94, 22, 4, 20, -23, -33, 123, 42,
            -87, 105, -5, -97, -72, 93, -53, -117, -34, 58, 89, 103, -14, -9, -32,
            41, 24, 15, 50, 48, 49, 54, 48, 53, 51, 48, 48, 54, 53, 50, 53, 55, 90,
            48, 88, 48, 86, 48, 65, 48, 9, 6, 5, 43, 14, 3, 2, 26, 5, 0, 4, 20, -96,
            18, -27, 62, -72, -39, 106, -4, -19, -38, 37, 96, 1, 89, 111, 33, -84,
            -11, -65, 88, 4, 20, -23, -33, 123, 42, -87, 105, -5, -97, -72, 93, -53,
            -117, -34, 58, 89, 103, -14, -9, -32, 41, 2, 8, 117, -42, 71, 12, 111,
            23, -17, -47, -128, 0, 24, 15, 50, 48, 49, 54, 48, 53, 51, 48, 48, 54,
            53, 50, 53, 55, 90, -95, 26, 48, 24, 48, 22, 6, 9, 43, 6, 1, 5, 5, 7,
            48, 1, 2, 4, 9, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 13, 6, 9, 42,
            -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 3, -127, -127, 0, -127, -30, 102,
            85, 109, 67, 84, -73, -37, 126, -94, 107, 125, -81, 117, -123, -53, -97,
            -26, 122, -126, 14, -26, -107, -121, 60, -109, -85, -59, -22, -96, -96,
            39, 76, 119, 105, -50, -23, -56, -51, -66, -64, 25, 110, 50, 67, 89,
            -111, -77, -50, -75, -81, 120, 51, 60, 25, -126, -91, 43, -75, 94, -77,
            32, -76, -22, -68, -29, 39, 70, 11, 48, 32, -107, -63, -49, 11, -68, 52,
            -64, -7, -123, 107, 13, -100, -111, -73, -102, -9, 8, 45, 51, 84, -105,
            -2, -75, -16, 42, -32, 107, 99, -71, 16, 63, 110, -92, -94, -77, 35, 71,
            41, -18, 92, -81, 51, 124, 73, 75, 29, -24, -53, -87, 88, -22, -121,
            115, 33, 87, -1, -96, -126, 2, 98, 48, -126, 2, 94, 48, -126, 2, 90, 48,
            -126, 1, -61, -96, 3, 2, 1, 2, 2, 8, 117, -42, 71, 12, 111, 23, -17,
            -47, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 11, 5, 0, 48, 52,
            49, 13, 48, 11, 6, 3, 85, 4, 5, 19, 4, 49, 50, 51, 52, 49, 35, 48, 33,
            6, 3, 85, 4, 3, 12, 26, 73, 110, 116, 101, 103, 114, 97, 116, 101, 100,
            79, 99, 115, 112, 82, 101, 115, 112, 111, 110, 115, 101, 84, 101, 115,
            116, 48, 30, 23, 13, 49, 54, 48, 53, 51, 48, 48, 54, 52, 50, 53, 54, 90,
            23, 13, 49, 54, 48, 54, 48, 57, 48, 54, 53, 50, 53, 54, 90, 48, 52, 49,
            13, 48, 11, 6, 3, 85, 4, 5, 19, 4, 49, 50, 51, 52, 49, 35, 48, 33, 6, 3,
            85, 4, 3, 12, 26, 73, 110, 116, 101, 103, 114, 97, 116, 101, 100, 79,
            99, 115, 112, 82, 101, 115, 112, 111, 110, 115, 101, 84, 101, 115, 116,
            48, -127, -97, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0,
            3, -127, -115, 0, 48, -127, -119, 2, -127, -127, 0, -72, -35, 112, 1,
            58, 82, 75, 19, 28, -58, 72, 107, -126, 110, -76, 37, 29, -59, -101,
            -82, 125, -20, 23, 122, 49, 22, 3, -106, -53, -26, 36, -106, -47, 46,
            37, -98, -89, -28, -2, -118, -98, 77, 96, 42, 23, 110, -119, -74, -3,
            33, -111, -9, 61, 12, 20, 125, -15, -2, 9, 50, -125, -56, -34, -114, 80,
            84, -54, 111, -86, 74, -65, -45, 30, 110, 127, 64, 118, -59, 115, 36,
            73, -55, 99, 94, -44, 20, -84, 28, -33, -20, -115, 25, -57, 107, 72, 29,
            35, 109, 108, -98, -69, -102, 6, 46, 67, 81, -103, 15, 105, -9, 28, 83,
            27, 79, 112, -2, -28, 72, -128, -40, 4, 69, -4, 14, 83, 24, 16, -75, 2,
            3, 1, 0, 1, -93, 117, 48, 115, 48, 15, 6, 3, 85, 29, 19, 1, 1, -1, 4, 5,
            48, 3, 1, 1, -1, 48, 14, 6, 3, 85, 29, 15, 1, 1, -1, 4, 4, 3, 2, 1,
            -122, 48, 29, 6, 3, 85, 29, 14, 4, 22, 4, 20, -23, -33, 123, 42, -87,
            105, -5, -97, -72, 93, -53, -117, -34, 58, 89, 103, -14, -9, -32, 41,
            48, 31, 6, 3, 85, 29, 35, 4, 24, 48, 22, -128, 20, -23, -33, 123, 42,
            -87, 105, -5, -97, -72, 93, -53, -117, -34, 58, 89, 103, -14, -9, -32,
            41, 48, 16, 6, 3, 85, 29, 32, 4, 9, 48, 7, 48, 5, 6, 3, 41, 1, 1, 48,
            13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 11, 5, 0, 3, -127, -127, 0,
            23, 38, -109, 105, 76, -49, 21, -77, -21, -16, -117, -110, 41, -54, -87,
            -70, -52, 100, 4, 9, 103, -1, 74, 64, -126, -15, -24, 65, -50, 107, -17,
            -37, -67, -101, 98, -62, 60, 64, -26, 56, 92, 119, 45, -40, 97, -14,
            -55, -56, -32, -34, 41, 62, 1, 82, 29, -64, 78, -121, 102, 27, -7, 61,
            18, 32, 122, 9, -91, -20, 99, 28, 87, 0, -25, -14, -2, 50, -53, 48, 49,
            96, -74, -61, 54, -17, 36, -66, -36, -66, 115, -19, 78, -92, 10, -12,
            -127, -81, -84, -43, -53, 41, -81, -62, 45, -1, -13, -38, 2, 17, 103,
            54, -94, 38, -66, -56, -29, -28, -29, -30, 5, 58, 127, -62, 79, -98,
            -76, -80, 27, -93};
        try {
            printOCSPResponse(ocspResponse);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OCSPException e) {
            e.printStackTrace();
        } catch (OperatorCreationException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void printOCSPResponse(byte[] ocspResponse) throws IOException, OCSPException, OperatorCreationException {
        OCSPResp response = new OCSPResp(ocspResponse);
        if (response.getStatus() != 0) {
            System.out.println("OCSP response status: " + response.getStatus());
            return;
        }
        // Getting the basic OCSP response by using BouncyCastle's BasicOCSPResp
        BasicOCSPResp basicResponse = (BasicOCSPResp) response.getResponseObject();
        System.out.println("Basic response: " + basicResponse);
        // Creating reponse array by using BouncyCastle's SingleResp
        SingleResp[] responses = basicResponse.getResponses();
        // Printing additional certificate information (ID, Status, Updates, etc.)
        for (SingleResp resp : responses) {
            System.out.println("Certificate ID: " + resp.getCertID());
            System.out.println("Certificate Status: " + resp.getCertStatus()); // "null indicates good" - bc documentation
            System.out.println("Critical extentions OIDs: " + resp.getCriticalExtensionOIDs());
            System.out.println("This Update: " + resp.getThisUpdate());
            System.out.println("Next Update " + resp.getNextUpdate());
        }
    }
}