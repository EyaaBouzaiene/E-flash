///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package test.event;
//
//import Event.crud.Evenement;
//import Event.crud.reservation;
//
//import Service.EventService;
//
//import Service.ReservationService;
//import events.tools.Maconnexion;
//
//import java.sql.Date;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author ideapadGAMING
// */
//public class test {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//     Date d1=Date.valueOf("2022-06-11");
//        Maconnexion mc = Maconnexion.getInstance();
//        Evenement E = new Evenement("ggg", "description", 8, 9,d1 );
//        Evenement e =new Evenement(47, "happy hour", "happy hour", 5, 6, d1);
//        EventService ev = new EventService();
//     
//////        ev.ajouterEevenement(e);
////System.out.println(ev.TriS());
//////        ev.update(e);
//////        ev.supprimer(e);
//////        
////        try {
////            
////            System.out.println(ev.afficherEvenement());
////        } catch (SQLException ex) {
////            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
////        }
//        
//        
////        System.out.println(ev.RECHERCHE(27));
//        
////  resrvation
////  reservation  r = new reservation(4, "bbbb", "prenom", "gmail", 7);
////    reservation  ra = new reservation("iiiiiiiiiii", "prenom", "gmail", 77);
////        ReservationService rv = new ReservationService();
////        rv.ajouterReservation(r);
////          try {
////            
////            System.out.println(rv.afficherReservation());
////        } catch (SQLException ex) {
////            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
////        }
////       
////
////  rv.update(r);
////  rv.supprimer(r);
////    }
//    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
//}
