package dao;

import java.util.List;
import java.util.Map;

import bean.Bean;

import exception.integration.IntegrationException;

public interface AbstractDao{
	public int update(Map map)throws IntegrationException;
	public int insert(Map map)throws IntegrationException;
	public Bean read(Map map)throws IntegrationException;
	public List readAll(Map map)throws IntegrationException;
	public int delete(Map map)throws IntegrationException;
}