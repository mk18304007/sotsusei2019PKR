package dao;

import java.util.List;
import java.util.Map;

import bean.Bean;

public interface AbstractDao{
	public int update(Map map);
	public int insert(Map map);
	public Bean read(Map map);
	public List readAll(Map map);
}