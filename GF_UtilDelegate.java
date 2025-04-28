import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import greenfoot.GreenfootImage;
import greenfoot.UserInfo;
import greenfoot.platforms.GreenfootUtilDelegate;

public class GF_UtilDelegate implements GreenfootUtilDelegate {
    @Override
    public Iterable<String> getSoundFiles() {
        // Misal kembalikan list kosong kalau gak perlu suara
        return new ArrayList<>();
    }

    @Override
    public String getGreenfootLogoPath() {
        return "empty.png"; // Ganti dengan path logo Greenfoot yang sesuai
    }

    @Override
    public UserInfo getCurrentUserInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentUserInfo'");
    }

    @Override
    public List<UserInfo> getNearbyUserInfo(int arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNearbyUserInfo'");
    }

    @Override
    public URL getResource(String name) {
        return getClass().getClassLoader().getResource(name);
    }

    @Override
    public List<UserInfo> getTopUserInfo(int arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTopUserInfo'");
    }

    @Override
    public GreenfootImage getUserImage(String arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserImage'");
    }

    @Override
    public String getUserName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserName'");
    }

    @Override
    public boolean isStorageSupported() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isStorageSupported'");
    }

    @Override
    public boolean storeCurrentUserInfo(UserInfo arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'storeCurrentUserInfo'");
    }
    
}
