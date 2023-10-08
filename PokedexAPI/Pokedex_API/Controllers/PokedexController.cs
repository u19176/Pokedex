using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using Pokedex_API.Data;
using Pokedex_API.Models;

namespace Pokedex_API.Controllers
{
    [ApiController]
    [Route("api/pokedex")]
    public class PokedexController : ControllerBase
    {
        private PokedexContext _context;

        public PokedexController(PokedexContext context)
        {
            _context = context;
        }

        [HttpGet]
        [Route("getdex")]
        public ActionResult<List<Pokemon>> GetAll()
        {
            return _context.pokemon_data.ToList();
        }

        [HttpGet]
        [Route("getid/{id}")]
        public ActionResult<Pokemon> GetID(int id)
        {
            var pokemon = _context.pokemon_data.FirstOrDefault(p => p.id_data == id);

            if (pokemon == null)
            {
                return NotFound();
            }

            return pokemon;
        }

        [HttpPost]
        [Route("create")]
        public ActionResult<Pokemon> CreatePokemon(Pokemon pokemon)
        {
            _context.pokemon_data.Add(pokemon);
            _context.SaveChanges();

            return CreatedAtAction("GetID", new { id = pokemon.id_data }, pokemon);
        }

        [HttpPut]
        [Route("update/{id}")]
        public IActionResult UpdatePokemon(int id, Pokemon updatedPokemon)
        {
            if (id != updatedPokemon.id_data)
            {
                return BadRequest();
            }

            _context.Entry(updatedPokemon).State = EntityState.Modified;

            try
            {
                _context.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PokemonExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        [HttpDelete]
        [Route("delete/{id}")]
        public IActionResult DeletePokemon(int id)
        {
            var pokemon = _context.pokemon_data.Find(id);

            if (pokemon == null)
            {
                return NotFound();
            }

            _context.pokemon_data.Remove(pokemon);
            _context.SaveChanges();

            return NoContent();
        }

        [HttpGet]
        [Route("getdexab/{id}")]
        public ActionResult<List<string>> GetAbilitiesForPokemon(int id)
        {
            var abilities = _context.pokemon_abilities
                .Where(pa => pa.id_pokemon == id)
                .Select(pa => pa.Ability.nome_abilidade)
                .ToList();

            if (abilities.IsNullOrEmpty())
            {
                return NotFound();
            }

            return Ok(abilities);
        }

        [HttpGet]
        [Route("getdexmv/{id}")]
        public ActionResult<List<string>> GetMovesForPokemon(int id)
        {
            var moves = _context.pokemon_moves
                .Where(pm => pm.id_pokemon == id)
                .Select(pm => pm.Move)
                .ToList();

            if (moves.IsNullOrEmpty())
            {
                return NotFound();
            }

            return Ok(moves);
        }


        private bool PokemonExists(int id)
        {
            return _context.pokemon_data.Any(p => p.id_data == id);
        }
    }
}
