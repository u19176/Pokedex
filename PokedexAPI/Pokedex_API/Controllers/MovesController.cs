using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Pokedex_API.Data;
using Pokedex_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Pokedex_API.Controllers
{
    [ApiController]
    [Route("api/moves")]
    public class MovesController : ControllerBase
    {
        private readonly PokedexContext _context;

        public MovesController(PokedexContext context)
        {
            _context = context;
        }

        [HttpGet("/getmoves")]
        public async Task<ActionResult<IEnumerable<Moves>>> GetMoves()
        {
            return await _context.moves.ToListAsync();
        }

        [HttpGet("/getmv/{id}")]
        public async Task<ActionResult<Moves>> GetMove(int id)
        {
            var move = await _context.moves.FindAsync(id);

            if (move == null)
            {
                return NotFound();
            }

            return move;
        }

        [HttpPost("/createmv")]
        public async Task<ActionResult<Moves>> PostMove(Moves move)
        {
            _context.moves.Add(move);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetMove", new { id = move.id_move }, move);
        }

        [HttpPut("/updatemv/{id}")]
        public async Task<IActionResult> PutMove(int id, Moves move)
        {
            if (id != move.id_move)
            {
                return BadRequest();
            }

            _context.Entry(move).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!MoveExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return Ok();
        }

        [HttpDelete("/deletemv/{id}")]
        public async Task<IActionResult> DeleteMove(int id)
        {
            var move = await _context.moves.FindAsync(id);
            if (move == null)
            {
                return NotFound();
            }

            _context.moves.Remove(move);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool MoveExists(int id)
        {
            return _context.moves.Any(e => e.id_move == id);
        }
    }
}
