package com.ust.carts.Controller;
import com.ust.carts.Model.Carts;
import com.ust.carts.Repository.Cartsrepo;
import com.ust.carts.VO.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class Cartscontroller {

    @Autowired
    private Cartsrepo cartsrepo;

    @PostMapping
    public ResponseEntity<Carts> createUser(@RequestBody Carts cart) {
        Carts savedUser = cartsrepo.save(cart);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

     //Read operation
    @GetMapping("/{userid}")
    public ResponseEntity<List<Carts>> getUser(@PathVariable("userid") Integer userid) {
        var cart = cartsrepo.findByUserid(userid).orElse(null);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userid}")
    public ResponseEntity<Carts> updateUser(@PathVariable("userid") Integer userid, @RequestBody Carts cart) {
        if (cartsrepo.existsByUserid(userid)) {
            cart.setUserid(userid);
            Carts updatedUser = cartsrepo.save(cart);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    Delete operation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        if (cartsrepo.existsById(id)) {
            cartsrepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    ResponseEntity<List<Carts>> findAll(){
        List<Carts> products = cartsrepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);

    }
}
