package be.kdg.programming3.prog3_spring.repository;

import be.kdg.programming3.prog3_spring.Domain.Beer;
import be.kdg.programming3.prog3_spring.Domain.Containers;
import be.kdg.programming3.prog3_spring.Domain.Order;
import be.kdg.programming3.prog3_spring.Domain.Quantities;
import be.kdg.programming3.prog3_spring.exceptions.DataBaseException;
import be.kdg.programming3.prog3_spring.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class BeerJBDCRepository implements BeerRepository {
    private Logger logger = LoggerFactory.getLogger(BeerJBDCRepository.class);

    private String url;
    private String username;
    private String password;

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert beerInserter;

   /* public BeerJBDCRepository(@Value("${spring.datasource.url}") String url,
                                 @Value("${spring.datasource.username}") String username,
                                 @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }*/

    public BeerJBDCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        beerInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("beer").usingGeneratedKeyColumns("idbeer");
    }

   @Override
    public Beer findById(int idBeer){
        logger.debug("Reading beer: {}", idBeer);
       return jdbcTemplate.queryForObject("SELECT * FROM BEER WHERE IDBEER = ?",
               this::mapRow,
               idBeer);
    }

    @Override
    public List<Beer> readAllBeers(){
        logger.debug("Reading all beers");
        List<Beer> beers = new ArrayList<>();
            //Create connection - statement - executeQuery - return List
           /* List<Beer> beers = new ArrayList<>();
            try (
                    Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement statement = connection.prepareStatement("select * from beer")) {
                try (ResultSet resultSet =
                             statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("idbeer");
                        String name = resultSet.getString("name");
                        double abv = resultSet.getDouble("abv");
                        Optional<Integer> plato = Optional.ofNullable(resultSet.getInt("plato"));
                        String style = resultSet.getString("style");
                        Quantities quantity = Quantities.valueOf(resultSet.getString("quantity"));
                        int stock = resultSet.getInt("stock");
                        Containers container = Containers.valueOf(resultSet.getString("container"));
                        String brewery = resultSet.getString("brewery");
                        double price = resultSet.getDouble("price");
                        String imageUrl = "";
                        switch(container){
                            case CAN ->  imageUrl = "/images/beer-can.png" ;
                            case BOTTLE ->  imageUrl ="/images/beer-bottle.png";
                            case KEG ->  imageUrl ="/images/beer-keg.png";
                        }
                        Beer beer = new Beer(id, name, abv, plato, style, quantity, stock,container, brewery, price, imageUrl);
                        beers.add(beer);
                    }
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new DataBaseException("Problem finding all beers", e);
            }
        return beers;*/
        jdbcTemplate.query("SELECT * FROM BEER",
                (ResultSet rs) -> {
                    int id = rs.getInt("idbeer");
                    String name = rs.getString("name");
                    double abv = rs.getDouble("abv");
                    Optional<Integer> plato = Optional.ofNullable(rs.getObject("plato", Integer.class));
                    String style = rs.getString("style");
                    Quantities quantity = Quantities.valueOf(rs.getString("quantity"));
                    int stock = rs.getInt("stock");
                    Containers container = Containers.valueOf(rs.getString("container"));
                    String brewery = rs.getString("brewery");
                    double price = rs.getDouble("price");
                    String imageUrl = switch (container) {
                        case CAN -> "/images/beer-can.png";
                        case BOTTLE -> "/images/beer-bottle.png";
                        case KEG -> "/images/beer-keg.png";
                    };
                    Beer beer = new Beer(id, name, abv, plato, style, quantity, stock, container, brewery, price, imageUrl);
                    beers.add(beer);
                });
        return beers;
    }

    @Override
    public int getSize() {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        logger.debug("Reading size of all beers " + keyHolder.getKey().intValue());
        return keyHolder.getKey().intValue();
    }

    @Override
    public Beer save(Beer beer) {
        logger.info("Creating new beer: {}, with id: {}", beer, beer.getIdBeer());
        PreparedStatementCreatorFactory pscf = new
                PreparedStatementCreatorFactory("INSERT INTO BEER(NAME, ABV, PLATO, STYLE, QUANTITY, STOCK, CONTAINER, BREWERY, PRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                Types.VARCHAR,
                Types.DOUBLE,
                Types.INTEGER,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.INTEGER,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.NUMERIC);

        pscf.setReturnGeneratedKeys(true);

        Integer platoValue = beer.getPlato().orElse(null);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(List.of(
                beer.getName(),
                beer.getAbv(),
                platoValue,
                beer.getStyle(),
                beer.getQuantity().toString(),
                beer.getStock(),
                beer.getContainers().toString(),
                beer.getBrewery(),
                beer.getPrice()
        ));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        beer.setIdBeer(keyHolder.getKey().intValue());
        return beer;
    }


    public int getStock(int idBeer){
        return findById(idBeer).getStock();
    }

    private Beer mapRow(ResultSet rs, int i) throws SQLException {
        Containers container = Containers.valueOf(rs.getString("container"));
        String imageUrl = "";
        switch(container){
            case CAN ->  imageUrl = "/images/beer-can.png" ;
            case BOTTLE ->  imageUrl ="/images/beer-bottle.png";
            case KEG ->  imageUrl ="/images/beer-keg.png";
        }

        Beer beer  = new Beer(rs.getInt("idbeer"),
                rs.getString("name"),
                rs.getDouble("abv"),
                Optional.ofNullable(rs.getInt("plato")),
                rs.getString("style"),
                Quantities.valueOf(rs.getString("quantity")),
                rs.getInt("stock"),
                container,
                rs.getString("brewery"),
                rs.getDouble("price"),
                imageUrl);

       /* jdbcTemplate.query("SELECT * FROM BEER_ORDER WHERE beerid = ?",
                (ResultSet beerRs, int beerRowNum) -> {
                    int quantity = beerRs.getInt("QUANTITY");
                    int orderId = beerRs.getInt("ORDERID");
                    beer.setOrders(orderId);
                    return null;
                },
                rs.getInt("idbeer")
        );*/
        return beer;
    }

    @Override
    public void updateBeer(Beer beer){
        try{
            Integer platoValue = beer.getPlato().orElse(null);
            jdbcTemplate.update("UPDATE BEER SET NAME = ?, ABV = ?, PLATO = ?, STYLE = ?, QUANTITY = ?, STOCK = ?, CONTAINER =?, BREWERY =?, PRICE =? WHERE IDBEER = ?",
                    beer.getName(), beer.getAbv(), platoValue, beer.getStyle(), beer.getQuantity().toString(), beer.getStock(), beer.getContainers().toString(), beer.getBrewery(), beer.getPrice(), beer.getIdBeer());
        } catch (DataAccessException e){
            logger.error(e.getMessage());
            throw new DataBaseException("Error updating beer: " + e);
        }
    }

}
