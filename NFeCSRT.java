import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.xml.bind.DatatypeConverter;

public class NFeCSRT {

    /**
     * Gera o hashCSRT a partir do token CSRT e da chave de acesso da NFe.
     *
     * @param idCSRT   Identificador do CSRT fornecido pelo Fisco.
     * @param tokenCSRT Token CSRT fornecido pelo Fisco.
     * @param chaveNFe  Chave de acesso da NFe.
     * @return hashCSRT em Base64.
     */
    public static String gerarHashCSRT(String tokenCSRT, String chaveNFe) {
        try {
            // Concatena tokenCSRT + chaveNFe
            String dados = tokenCSRT + chaveNFe;

            // Gera o hash SHA-1
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(dados.getBytes(StandardCharsets.UTF_8));

            // Converte a hash SHA-1 para hexadecimal
            String sha1Hex = String.format("%040x", new BigInteger(1, digest.digest()));

            // Converte a hash hexadecimal para array de bytes
            byte[] sha1Bytes = DatatypeConverter.parseHexBinary(sha1Hex);

            // Retorna a Base64 do hash
            return Base64.getEncoder().encodeToString(sha1Bytes);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Exemplo de uso
        String idCSRT = "01"; // Identificador do CSRT (exemplo)
        String tokenCSRT = "12345678901234567890"; // Token CSRT fictício (20 caracteres numéricos)
        String chaveNFe = "43191234567890123456789012345678901234567890"; // Chave de acesso da NFe (44 dígitos)

        String hashCSRT = gerarHashCSRT(tokenCSRT, chaveNFe);

        System.out.println("idCSRT: " + idCSRT);
        System.out.println("hashCSRT: " + hashCSRT);
    }
}
