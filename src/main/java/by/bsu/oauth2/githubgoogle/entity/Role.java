package by.bsu.oauth2.githubgoogle.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority
{
  USER;

  @Override
  public String getAuthority()
  {
     return name();
  }
}