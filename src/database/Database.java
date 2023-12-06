/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author dougl
 */
public abstract class Database {
    
    protected final static String DB_BASE_URL = "jdbc:mysql://localhost";
    protected final static String DB_USER = "ooc2023";
    protected final static String DB_PASSWORD = "ooc2023";
    protected final static String DB_NAME = "IrelandRevenueData";
    protected final static String TABLE_NAME_USERDATA = "UserData"; //Alterar para as nossas tables
    protected final static String TABLE_NAME_TAXINFO = "TaxInfo"; //Alterar para as nossas tables
    protected final static String TABLE_NAME_PARTNER_TAXINFO = "PartnerTaxInfo"; //Alterar para as nossas tables
    
}
