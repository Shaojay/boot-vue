package com.jay.boot.common.config;

/**
 * RsaKey 公钥和密钥
 *
 * @author shao.meng
 * @since 1.0
 * Date 2019/4/14 13:38
 */
public class RsaKey {
    
    private String publicKey;
    
    private String privateKey;
    
    public String getPublicKey() {
        return publicKey;
    }
    
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
    
    public String getPrivateKey() {
        return privateKey;
    }
    
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
