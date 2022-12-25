
package services;



import model.paiement;
import interfaces.IPaiement;
import util.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class paiementservices  implements IPaiement{
    Connection cnx;

    public paiementservices ()  {
        cnx=MaConnexion.getInstance().getCnx();
}
    
    

    @Override
    public  void AddPaiement(paiement p) {
        PreparedStatement pst ;
        
        try {
            String sql="INSERT INTO paiement(mode_paiement,num_carte,cryptograme,date_exp)VALUES(?,?,?,?)";
            pst=cnx.prepareStatement(sql);
            pst.setString(1,p.getMode_paiement());
            pst.setInt(2,p.getNum_carte());
            pst.setInt(3,p.getCryptograme());
            pst.setString(4,p.getDate_exp());
            if(pst.executeUpdate()>0) 
                System.out.println("request send successfully!!!");
            else 
                System.out.println("failed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     ObservableList<paiement>oblist2=FXCollections.observableArrayList();
     
    @Override
    public ObservableList<paiement> AfficherPaiement()  {
        try{
        Statement stm=cnx.createStatement();
        String query="select* from `paiement`";
        ResultSet rst=stm.executeQuery(query);
        paiement P=new paiement();
        while(rst.next()){
            P.setId(rst.getInt("id"));
            P.setMode_paiement(rst.getString("mode_paiement"));
            P.setNum_carte(rst.getInt("num_carte"));
            P.setCryptograme(rst.getInt("cryptograme"));
            P.setDate_exp(rst.getString("date_exp"));
         oblist2.add(P);   
        }
        return oblist2;
        }catch(Exception e){
            
        }
        return null;
    }
    
    
    public List<String> AfficherModePaiement()  {
       List<String> p=new ArrayList<String>();
        try{
        Statement stm=cnx.createStatement();
        String query="select distinct mode_paiement from `paiement`";
        ResultSet rst=stm.executeQuery(query);
        IPaiement P=new paiementservices();
        while(rst.next()){
           
         p.add( rst.getString("mode_paiement"));   
        }
        
        }catch(Exception e){
            
        }
        return p;
    }
    
    public paiement AfficherPaiement(String mode_paiement)  {
        try{
        Statement stm=cnx.createStatement();
        String query="select * from paiement where mode_paiement='"+mode_paiement+"'";
        ResultSet rst=stm.executeQuery(query);
        paiement P=new paiement();
        while(rst.next()){
       P.setId(rst.getInt("id"));
            P.setMode_paiement(rst.getString("mode_paiement"));
            P.setNum_carte(rst.getInt("num_carte"));
            P.setCryptograme(rst.getInt("cryptograme"));
            P.setDate_exp(rst.getString("date_exp")); 
            return  P;
        }
        
        }catch(Exception e){
            
        }
        return null;
    }

    @Override
    public void DeletePaiement(paiement p) {
        int n2=0;
        PreparedStatement st;
        try {
            st=cnx.prepareStatement("DELETE FROM `paiement` WHERE `id`=?");
            st.setInt(1,p.getId());
            n2=st.executeUpdate();
            if(n2>0)
                System.out.println("Supprimé");
            else 
                
                System.out.println("non_supprimé");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void ModifyPaiement(paiement p) {
        String sql2="UPDATE paiement SET mode_paiement=?,num_carte=?,cryptograme=?,date_exp=?  WHERE id=? ";
        
        try {
            PreparedStatement pstmt=cnx.prepareStatement(sql2);
            pstmt.setString(1, p.getMode_paiement());
            pstmt.setInt(2,p.getNum_carte());
            pstmt.setInt(3, p.getCryptograme());
            pstmt.setString(4,p.getDate_exp());
            pstmt.setInt(5,p.getId());
            if(pstmt.executeUpdate()>0){
                System.out.println("Parfait paiement a ete modifiee avec succees ");
                
                   
            }
            else 
                System.out.println("Echec de modification");
            pstmt.close();
            
                
            
            
            
        } catch (SQLException ex) {
            System.out.println("Modify paiement=="+ex.getMessage());
        }
    }

    @Override
    public paiement getPaiementById(int id) {
        paiement p=new paiement();
        
        try {
            String sql="SELECT * FROM paiement WHERE id="+id;
            Statement stm=cnx.createStatement();
            ResultSet rst=stm.executeQuery(sql);
            while(rst.next()){
                p.setMode_paiement(rst.getString("mode_paiement"));
                p.setNum_carte(rst.getInt("num_carte"));
                p.setCryptograme(rst.getInt("cryptograme"));
                p.setDate_exp(rst.getString("date_exp"));
                
            
            
            }
        } catch (SQLException ex) {
            System.out.println("error"+ex.getMessage());
        }
        return p;
    }


}


